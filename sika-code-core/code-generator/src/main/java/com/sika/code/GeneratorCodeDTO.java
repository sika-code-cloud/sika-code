package com.sika.code;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author daiqi
 * @create 2019-05-15 15:10
 */
@Data
@Accessors(chain = true)
public class GeneratorCodeDTO {
    /** 父包 */
    private String parentPackage;

    /** common类path */
    private String pathForCommon;
    /** dataaccess包path */
    private String pathForDataaccess;
    /** core的path */
    private String pathForCore;
    /** web的path */
    private String pathForWeb;
    /** api的path */
    private String pathForApi;
    /** xml的path */
    private String pathForXml;

    /** 数据库url */
    private String dataBaseUrl;
    /** 数据库名称 */
    private String dataBaseName;
    /** 数据库端口 */
    private String dataBasePort;
    /** 数据库用户名 */
    private String username;
    /** 数据库密码 */
    private String password;

    /** 表名 */
    private String tableName;
    /** 表前缀 */
    private String subPrefix;
    /** 作者 */
    private String author;

    /** 模块的统一包名 */
    private String modulePackage;

    private String [] extendsFields;
}
