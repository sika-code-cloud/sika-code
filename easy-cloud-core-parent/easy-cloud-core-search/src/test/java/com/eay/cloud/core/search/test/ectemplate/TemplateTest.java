package com.eay.cloud.core.search.test.ectemplate;

import com.alibaba.fastjson.JSON;
import com.easy.cloud.core.search.EcCoreSearchApplication;
import com.easy.cloud.core.search.core.EcElasticsearchTemplate;
import com.easy.cloud.core.search.entry.MyLog2;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.PathResource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Title: TemplateTest
 * @Description:
 * @Author tudou
 * @Date 2018/8/2 20:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcCoreSearchApplication.class)
public class TemplateTest {
    @Autowired
    private EcElasticsearchTemplate template;
    @Test
    public void testIndexType() {
        template.deleteIndex(MyLog2.class);
        template.createIndex(MyLog2.class);
        template.putMapping(MyLog2.class);
//        template.deleteIndex(MyLog2.class);
//        template.refresh(MyLog2.class);
    }

    @Test
    public void testGetInfo() throws Exception {
        ElasticsearchTemplate elasticsearchTemplate;
//        ClusterState
        Map<String, String> setting = template.getSetting("mylog");
        Map mapping = template.getMapping("mylog", "log");
        List<AliasMetaData> list = template.queryForAlias("mylog");
        for (AliasMetaData aliasMetaData : list) {
            System.out.println(JSON.toJSONString(aliasMetaData));
//            System.out.println(JSON.toJSONString(aliasMetaData,true));
        }
        System.out.println(JSON.toJSONString(setting, true));
        ///D:/cloud/easy-cloud/easy-cloud-core-parent/easy-cloud-core-search/com/easy/cloud/core/search/
//        String path = EcCoreSearchApplication.class.getResource("").getPath().replace("/target/classes", "/src/main/java");
//        String path2 = EcCoreSearchApplication.class.getResource("").getPath().split("/target/classes")[0];
//        EcElasticsearchTemplate.wirteFileFromClasspath(String.format("%s%s/%s_%s.json", path, "test/setting", "mylog", "setting"), JSON.toJSONString(setting, true));
//        EcElasticsearchTemplate.wirteFileFromClasspath(String.format("%s%s/%s_%s.json", path, "test/mapping", "mylog", "mapping"), JSON.toJSONString(mapping, true));

//        File file = ResourceUtils.getFile("classpath:mapping/mylog2_mapping.json");
//        File file = ResourceUtils.getFile(String.format("classpath:%s/%s_%s.json","classpath:mapping", "mylog", "mapping"));
//        EcElasticsearchTemplate.wirteFileFromClasspath(file, JSON.toJSONString(mapping, true));
//
//        File settingfile = ResourceUtils.getFile(String.format("classpath:%s/%s_%s.json","classpath:setting", "mylog", "setting"));
//        EcElasticsearchTemplate.wirteFileFromClasspath(settingfile, JSON.toJSONString(setting, true));
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/target")[0]+"src/main//resource/";
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/target")[0]+"/src/main//resources/";
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/target")[0]+"/src/test/resources/";
        EcElasticsearchTemplate.wirteFileFromClasspath(String.format("%s%s/%s_%s.json", path, "setting", "mylog", "setting"), JSON.toJSONString(setting, true));
        EcElasticsearchTemplate.wirteFileFromClasspath(String.format("%s%s/%s_%s.json", path, "mapping", "mylog", "mapping"), JSON.toJSONString(mapping, true));
//        System.out.println(setting);
//        System.out.println(mapping);
//        System.out.println(JSON.toJSONString(setting));
//        System.out.println(JSON.toJSONString(mapping));
//        D:\cloud\easy-cloud\easy-cloud-core-parent\easy-cloud-core-search\src\test\resources\setting
//        D:\cloud\easy-cloud\easy-cloud-core-parent\easy-cloud-core-search\src\test\resource\setting\mylog_setting.json
    }

    public static void main(String[] args) throws IOException {
        PathResource pathResource = new PathResource("/application.properties");
        System.out.println("1:"+pathResource.getPath());
        String path = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        String path0 = ResourceUtils.getURL(ResourceUtils.FILE_URL_PREFIX).getPath();
        System.out.println("path0:"+path0);
        String path1 = EcCoreSearchApplication.class.getResource("").getPath().replace("/target/classes", "/src/main/java");
        String path2 = EcCoreSearchApplication.class.getResource("").getPath().split("/target")[0];
        System.out.println(path2);
        System.out.println(path1);
        System.out.println(System.getProperty("classpath"));
        String t = Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/target")[0];
        System.out.println(t);

        File file = ResourceUtils.getFile("classpath:mapping/mylog2_mapping.json");
        System.out.println("file:"+file.getPath());
        if(file.exists()){
            File[] files = file.listFiles();
            if(files != null){
                for(File childFile:files){
                    System.out.println(childFile.getName());
                }
            }
        }
    }
}
