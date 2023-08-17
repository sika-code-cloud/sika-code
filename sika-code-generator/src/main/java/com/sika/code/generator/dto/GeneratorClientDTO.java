package com.sika.code.generator.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeneratorClientDTO {
    private String author;

    private List<String> tableNames;
    private String tablePrefix;

    private String projectPrefix;
    private String modulePackagePrefix;

    private String ip;
    private Integer port;
    private String dbName;
    private String dbPassword;
    private String dbUsername;

    private boolean generateDomain = true;
    private boolean generateController = true;
    private Class<?> primaryKeyClass = Long.class;

    private String applicationClassName;
    private String applicationSimpleName;
    private String [] ignoreColumns;
}