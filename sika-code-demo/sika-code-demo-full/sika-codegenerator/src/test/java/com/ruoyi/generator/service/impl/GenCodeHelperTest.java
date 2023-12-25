package com.ruoyi.generator.service.impl;

import com.ruoyi.generator.controller.pg.PgGentableProvider;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.Starter;
import common.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.URI;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author lancelot
 * @version 1.0
 * @date 2022/9/1 上午9:32
 */
class GenCodeHelperTest {

    @Test
    void getTemplateContent() {

        String sql="TABLESPACE pg_default;\n" +
                "\n" +
                "ALTER TABLE IF EXISTS pay.t_pay_transaction\n" +
                "    OWNER to postgres;\n" +
                "\n" +
                "COMMENT ON COLUMN pay.t_pay_transaction.transaction_id\n" +
                "    IS '支付流水号';";

        String s = sql.replaceAll("ALTER.*[\r\n]?.*OWNER.*", "");

        System.out.println(s);


    }

    @Test
    void gen() throws Exception{

        genBySqlScript("ddl.sql");
    }


    void genBySqlScript(String classpathDDLFileName) throws Exception{

        File file = ResourceUtils.getFile(this.getClass().getClassLoader().getResource(classpathDDLFileName));
        String sql = FileUtils.readFileToString(file);

        List<GenTable> genTables = new PgGentableProvider().getGenTable(sql, "t_");

        for (GenTable genTable : genTables) {

            Starter starter=new Starter();
            starter.setProjName("demoProj");
            starter.setVersion("1.0.0");
            starter.setGroupId("com.sika.xx");
            starter.setArtifactId("dubbo-xx");
            starter.setProjDesc("dubbo-xx-desc");
            starter.setApolloId("dddorder");
            starter.setDdl(sql);


            String resourceDirectory = "/vm/codeTemplate/dubbo";

            VelocityEngine ve=new VelocityEngine();
            ve.setProperty("resource.loaders", "class");
            ve.setProperty("resource.loader.class.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            ve.init();

            VelocityContext context = new VelocityContext();

            context.put("columns", genTable.getColumns());
            context.put("indexColumns", genTable.getIndexColumns());
            context.put("pkColumn",genTable.getPkColumn());
            context.put("packageName","com.sika.xx");
            context.put("ClassName",genTable.getClassName());
            context.put("className", StringUtils.uncapitalize(genTable.getClassName()));
            context.put("starter",starter);
            context.put("tableName", genTable.getTableName());
            context.put("author","lancelot");
            context.put("datetime", LocalDateTime.now().toString());


            Path path;

            URI uri = getClass().getResource(resourceDirectory).toURI();

            if (uri.getScheme().equals("jar")) {

                FileSystem fileSystem;

                try {
                    fileSystem = FileSystems.getFileSystem(uri);
                } catch (FileSystemNotFoundException ex) {
                    fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
                }
                path = fileSystem.getPath("/BOOT-INF/classes" + resourceDirectory);
            } else {
                // Not running in a jar, so just use a regular filesystem path
                path = Paths.get(uri);
            }

            ResourceWalker resourceWalker=new ResourceWalker(path,resourceDirectory);

            File dir = new File("/home/lancelot/code/demo/demo/");

            resourceWalker.loop(dir,path,ve,context);
        }
    }
}