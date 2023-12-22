package com.sika.sharding.sphere.plus;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author xiaosk
 * @version 1.0
 * @date 2023/3/18 15:24
 */
public class SqlUtils {

    public static String getResourceAsString(Class clazz, String name) {
        try {
            URL url = clazz.getClassLoader().getResource("");
            if (null == url) {
                throw new RuntimeException(String.format("读取sql文件失败：%s", name));
            }
            return FileUtils.readFileToString(new File(url.getPath(), name), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(String.format("读取sql文件失败：%s", name), e);
        }
    }
}