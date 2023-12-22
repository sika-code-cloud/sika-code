package com.ruoyi.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipOutputStream;

import com.ruoyi.generator.domain.Starter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.service.IGenTableService;
import com.ruoyi.generator.util.VelocityInitializer;
import com.ruoyi.generator.util.VelocityUtils;

/**
 * 业务 服务层实现
 *
 * @author ruoyi
 */
@Service
public class GenTableServiceImpl implements IGenTableService {

    private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);

    @Override
    public byte[] downloadProjCode(Starter starter, List<GenTable> genTables) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // or folder.
        String property = "java.io.tmpdir";
        String randomDir = UUID.randomUUID().toString().replace("-", "");

        // Get the temporary directory and print it.
        String tempDir = System.getProperty(property) + File.separator + randomDir;

        File tempOutputDir = new File(tempDir);
        try {

            tempOutputDir.mkdirs();

            for (GenTable genTable : genTables) {
                genProjCode(starter, genTable, zip, tempOutputDir);
            }

            //the output dir should be ${projectType}/files, we rename to ${projectName}/files
            File finalOutPutDir = new File(tempOutputDir.getAbsolutePath() + File.separator + starter.getProjType());
            File renamed = new File(tempOutputDir + File.separator + starter.getProjName());
            finalOutPutDir.renameTo(renamed);

            //zip dir
            ZipUtility zipUtility = new ZipUtility();
            zipUtility.zip(Arrays.asList(renamed), zip);

        } catch (Exception ex) {

        } finally {
            IOUtils.closeQuietly(zip);
            try {
                FileUtils.deleteDirectory(tempOutputDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     * is it bad impl?
     */
    public void genProjCode(Starter starter, GenTable table, ZipOutputStream zip, File tempOutputDir)
            throws Exception {

        table.setPackageName(starter.getGroupId());

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(table);

        // 包路径
        String packageName = starter.getGroupId();

        context.put("author", starter.getAuthor());
        //override packageName
        context.put("packageName", packageName);
        //extra
        context.put("starter", starter);

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty("resource.loaders", "class");
        ve.setProperty("resource.loader.class.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ve.init();

        String resourceDirectory = "/vm/codeTemplate/" + starter.getProjType();
        URI uri = getClass().getResource(resourceDirectory).toURI();

        Path path;

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

        ResourceWalker resourceWalker = new ResourceWalker(path, resourceDirectory);
        resourceWalker.loop(tempOutputDir, path, ve, context);


    }
}