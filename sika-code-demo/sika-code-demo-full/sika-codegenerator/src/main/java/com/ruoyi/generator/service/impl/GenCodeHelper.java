package com.ruoyi.generator.service.impl;

import common.constant.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * @author lancelot
 */
public class GenCodeHelper {


    public static String getTemplateContent(VelocityEngine ve,VelocityContext context, String src) {
        StringWriter sw = new StringWriter();
        try {
            Template tpl = ve.getTemplate(src, Constants.UTF8);
            tpl.merge(context, sw);
        }catch (Exception ex){
            IOUtils.closeQuietly();
        }
        return sw.toString();
    }


    public static String resolveString(VelocityContext context, String src) {
        StringWriter sw = new StringWriter();
        try {
            Velocity.evaluate(context,sw,"",src);
        }catch (Exception ex){
            IOUtils.closeQuietly();
        }
        return sw.toString();
    }


    /**
     * gen code by looping directory(where directory may also be velocity templates )
     * @param file
     * @param destParent
     * @param ve
     * @param velocityContext
     * @param root
     * @throws IOException
     */
    public static void loop(File file,File destParent,VelocityEngine ve, VelocityContext velocityContext,String root) throws IOException {

        if(!destParent.exists()){
            destParent.mkdir();
        }

        if(file.isDirectory()){

            String name = file.getName();
            String realValue = resolveString(velocityContext, name);

            //try split by dot
            String[] parts = realValue.split("\\.");
            if(parts.length>1){

                StringJoiner stringJoiner=new StringJoiner(File.separator);

                for (String part : parts) {
                    stringJoiner.add(part);
                }

                File dirs=new File(destParent+File.separator+stringJoiner.toString());
                dirs.mkdirs();

                File[] files = file.listFiles();
                for (File subFile : files) {
                    loop(subFile,dirs,ve,velocityContext,root);
                }

            }else {

                File subDir=new File(destParent.getAbsolutePath() + File.separator +realValue);
                subDir.mkdir();

                File[] files = file.listFiles();
                for (File subFile : files) {
                    loop(subFile,subDir,ve,velocityContext,root);
                }
            }
            //is file
        }else {

            String fileContent = getTemplateContent(ve,velocityContext, file.getAbsolutePath().replace(root,""));
            //write file to desc
            String realFileName=resolveString(velocityContext,file.getName().replaceAll("\\.vm",""));

            File targetFile=new File(destParent.getAbsolutePath()+File.separator+realFileName);
            targetFile.createNewFile();

            FileUtils.write(targetFile,fileContent);

        }
    }


    public static void main(String[] args) throws Exception {
        long count = Files.list(Paths.get(URI.create("file:///home/lancelot/testhook/"))).count();

        System.out.println("count = " + count);

    }

}
