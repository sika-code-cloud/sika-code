package com.ruoyi.generator.service.impl;

/**
 * @author lancelot
 * @version 1.0
 * @date 2022/9/1 上午11:21
 */

import common.constant.Constants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

/**
 * @author lancelot
 */
public class ResourceWalker {

    private Path rootPath;

    private String prefix;

    private List<String> ignoreFileNames;

    public ResourceWalker(Path rootPath,String prefix) {
        this.rootPath = rootPath;
        this.prefix=prefix;
        ignoreFileNames=new ArrayList<>();
        ignoreFileNames.add(".gitkeep");
        ignoreFileNames.add(".gitignore");
    }

    /**
     * @param ve
     * @param context
     * @param src
     * @return
     */
    public String getTemplateContent(VelocityEngine ve, VelocityContext context, String src) {

        StringWriter sw = new StringWriter();
        try {
            Template tpl = ve.getTemplate(src, Constants.UTF8);
            tpl.merge(context, sw);
        } catch (Exception ex) {
            IOUtils.closeQuietly();
        }
        return sw.toString();
    }


    public String resolveString(VelocityContext context, String src) {

        StringWriter sw = new StringWriter();
        try {
            Velocity.evaluate(context, sw, "", src);
        } catch (Exception ex) {
            IOUtils.closeQuietly();
        }
        return sw.toString();
    }

    /**
     * @param destParent
     * @param ve
     * @param velocityContext
     * @throws Exception
     */
    public void loop(File destParent, Path templatePath, VelocityEngine ve, VelocityContext velocityContext) throws Exception {

        if (!destParent.exists()) {
            destParent.mkdir();
        }

        File directory=destParent;

        if (Files.isDirectory(templatePath)) {

            directory = writeDir(destParent, resolveString(velocityContext, templatePath.getFileName().toString()));
        }

        if (Files.isDirectory(templatePath)) {

            //recursively write children dir if has
            Iterator<Path> iterator = Files.list(templatePath).iterator();

            while (iterator.hasNext()) {

                Path pa = iterator.next();

                String name = pa.getFileName().toString();

                if(ignoreFileNames.contains(name)){
                    continue;
                }

                String realName = resolveString(velocityContext, name);

                boolean isDirectory = Files.isDirectory(pa);

                if (isDirectory) {

                    String resolvedDirectory = resolveDirectory(realName);
                    File dirs = new File(directory, resolvedDirectory);

                    dirs.mkdirs();

                    final Iterator<Path> children = Files.list(pa).iterator();

                    while (children.hasNext()) {
                        Path child = children.next();
                        loop(dirs, child, ve, velocityContext);
                    }
                }else {

                    writeFile(ve, velocityContext, directory, pa, realName);
                }
            }

            //is file
        } else {

            String realName = resolveString(velocityContext, templatePath.getFileName().toString());

            writeFile(ve, velocityContext, directory, templatePath, realName);
        }
    }

    private File writeDir(File destParent, String realName) {
        String resolvedDirectory = resolveDirectory(realName);
        File dirs = new File(destParent, resolvedDirectory);

        dirs.mkdirs();
        return dirs;
    }

    private void writeFile(VelocityEngine ve, VelocityContext velocityContext, File parent, Path pa, String realName) throws IOException {

        String fileContent = getTemplateContent(ve, velocityContext, prefix+File.separator+rootPath.relativize(pa).toString());
        String realFileName = realName.replaceAll("\\.vm", "");
        File targetFile = new File(parent + File.separator + realFileName);

        targetFile.createNewFile();
        FileUtils.write(targetFile, fileContent);
    }

    private String resolveDirectory(String realName) {
        //try split by dot
        String[] parts = realName.split("\\.");

        //multiple level directory
        StringJoiner stringJoiner = new StringJoiner(File.separator);

        for (String part : parts) {
            stringJoiner.add(part);
        }

        String resolved = stringJoiner.toString();

        return resolved;
    }
}