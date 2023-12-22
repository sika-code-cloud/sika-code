package com.ruoyi.generator.controller;

import com.sika.common.cache.core.operation.LocalCacheOperation;
import com.sika.common.cache.core.operation.RemoteCacheOperation;
import common.core.controller.BaseController;
import com.ruoyi.generator.controller.mysql.MysqlGentableProvider;
import com.ruoyi.generator.controller.pg.PgGentableProvider;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.Starter;
import com.ruoyi.generator.service.IGenTableService;
import common.utils.spring.SpringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool")
public class ProjectInitizController extends BaseController  {

    @Autowired
    private IGenTableService genTableService;

    static  Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");

    private ApplicationContext applicationContext;

    @Autowired
    LocalCacheOperation localCacheOperation;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        localCacheOperation.put("a","b");
        return "test";
    }

    /**
     * 查询代码生成列表
     * @return
     */
    @GetMapping("/jars")
    @ResponseBody
    public Map<String, List<Jar>> genList(GenTable genTable) throws FileNotFoundException {
        List<Jar> bomJars = bomJars();

        List<Jar> dubboJars = getJars(bomJars, "dependency/dubbo/pom.xml");
        List<Jar> gatewayJars = getJars(bomJars, "dependency/gateway/pom.xml");
        List<Jar> webJars = getJars(bomJars, "dependency/web/pom.xml");

        Map<String,List<Jar>> result=new HashMap<>();
        result.put("dubbo",dubboJars);
        result.put("gateway",gatewayJars);
        result.put("web",webJars);

        return result;
    }

    private List<Jar> getJars(List<Jar> bomJars,String pomPath) {
        List<Jar> dubboJars = new ArrayList<>();

        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(this.getClass().getClassLoader().getResourceAsStream(pomPath));
            List<Dependency> dependencies = model.getDependencies();

            for (Dependency dependency : dependencies) {

                Jar jar = new Jar();
                jar.setGroupId(dependency.getGroupId());
                jar.setArtifactId(dependency.getArtifactId());
                jar.setType(dependency.getType());
                dubboJars.add(jar);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //对比 赋值dubbo Jar version
        for (Jar dubboJar : dubboJars) {
            for (Jar bomJar : bomJars) {
                if(Objects.equals(dubboJar.getGroupId(),bomJar.getGroupId())
                  &&  Objects.equals(dubboJar.getArtifactId(),bomJar.getArtifactId())
                ){
                    dubboJar.setVersion(bomJar.getVersion());
                    break;
                }
            }
        }

        for (Jar dubboJar : dubboJars) {
            dubboJar.setFromParent(true);
        }

        return dubboJars;
    }


    /**
     * 查询代码生成列表
     */
    @GetMapping("/bomJars")
    @ResponseBody
    public List<Jar> bomJars() throws FileNotFoundException {
        return getBomJars("dependency/bom/pom.xml");
    }

    private List<Jar> getBomJars(String filepath) {
        List<Jar> jars = new ArrayList<>();

        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(this.getClass().getClassLoader().getResourceAsStream(filepath));
            DependencyManagement dependencyManagement = model.getDependencyManagement();
            List<Dependency> dependencies = dependencyManagement.getDependencies();

            Properties properties = model.getProperties();

            for (Dependency dependency : dependencies) {

                Jar jar = new Jar();

                jar.setVersion(dependency.getVersion());
                jar.setGroupId(dependency.getGroupId());
                jar.setArtifactId(dependency.getArtifactId());
                jar.setType(dependency.getType());

                String version = dependency.getVersion();
                Matcher matcher = pattern.matcher(version);

                if (matcher.find()) {
                    String group = matcher.group(1);
                    String property = properties.getProperty(group);
                    jar.setVersion(property);
                }

                jars.add(jar);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jars;
    }

    /**
     * 查询代码生成列表
     */
    @GetMapping("/selectJarPage")
    public String genList() throws FileNotFoundException {
        return "system/starter/selectJar";
    }

    /**
     * 生成代码（自定义路径）
     */
    @RequestMapping("/genCode")
    public void genCode(@RequestBody Starter starter, HttpServletRequest request, HttpServletResponse response) {

        String sql = starter.getDdl();

        if (StringUtils.isNotEmpty(sql)) {
            //创建表
            try {

                GentableProvider gentableProvider;

                if("postgres".equals(starter.getDbtype())){
                    gentableProvider=new PgGentableProvider();
                }else if("mysql".equals(starter.getDbtype())){
                    gentableProvider=new MysqlGentableProvider();
                }else {
                    throw new RuntimeException("暂不支持此种数据库类型");
                }

                List<GenTable> genTables = gentableProvider.getGenTable(sql,starter.getIgnorePrefix());

                //下载代码
                byte[] data = genTableService.downloadProjCode(starter, genTables);
                try {
                    genCode(response, data);
                } catch (IOException e) {
                    logger.error("生成代码出错");
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                byte[] data = e.getMessage().getBytes();
                try {
                    response.reset();
                    response.addHeader("Content-Length", "" + data.length);
                    response.setContentType("application/json; charset=UTF-8");
                    IOUtils.write(data, response.getOutputStream());
                } catch (IOException ex) {
                    logger.error("生成代码出错");
                }
            }
        }
    }


    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }


}