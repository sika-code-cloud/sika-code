package com.sika.code;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.sika.code.expand.TableInfoExpand;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成器助手类
 *
 * @author daiqi
 * @create 2019-05-09 15:33
 */
public class GenerateCodeHandler {

    protected String moduleName;
    protected String tableName;
    protected String[] tablePrefix;
    /**
     * ----------------路径配置 begin------------------
     */

    /** common类path */
    protected String pathForCommon;
    /** dataaccess包path */
    protected String pathForDataaccess;
    /** core的path */
    protected String pathForCore;
    /** web的path */
    protected String pathForWeb;
    /** api的path */
    protected String pathForApi;
    /** xml的path */
    protected String pathForXml;
    /**
     * ----------------路径配置 end------------------
     */
    protected String parentPackage;
    protected String[] extendsFields = {"id", "create_by", "update_by", "create_date", "update_date",
            "version", "available", "is_deleted", "remark"};
    /**
     * 代码生成器
     */
    AutoGenerator mpg = new AutoGenerator();
    /**
     * 全局配置
     */
    GlobalConfig gc = new GlobalConfig();
    /**
     * 数据源配置
     */
    DataSourceConfig dsc = new DataSourceConfig();

    /**
     * 包配置
     */
    PackageConfig pc = new PackageConfig();

    String projectPath = System.getProperty("user.dir");

    GeneratorCodeDTO generatorCodeDTO;
    /**
     * 策略配置
     */
    StrategyConfig strategy = new StrategyConfig();
    protected boolean buildFieldFlag = false;

    public void generateCode(GeneratorCodeDTO generatorCodeDTO) {
        this.generatorCodeDTO = generatorCodeDTO;

        this.pathForCommon = generatorCodeDTO.getPathForCommon();
        this.pathForDataaccess = generatorCodeDTO.getPathForDataaccess();
        this.pathForCore = generatorCodeDTO.getPathForCore();
        this.pathForWeb = generatorCodeDTO.getPathForWeb();
        this.pathForApi = generatorCodeDTO.getPathForApi();
        this.pathForXml = generatorCodeDTO.getPathForXml();

        parentPackage = generatorCodeDTO.getParentPackage();

        if (generatorCodeDTO.getExtendsFields() != null) {
            extendsFields = generatorCodeDTO.getExtendsFields();
        }
        init(generatorCodeDTO.getTableName(), generatorCodeDTO.getSubPrefix(), generatorCodeDTO.getAuthor());
        generateJava();
        generateXml();
    }

    public void init(String tableName, String subPrefix, String author) {
        if (author == null) {
            author = "";
        }
        this.tableName = tableName;
        String[] tablePrefix = {subPrefix};
        this.tablePrefix = tablePrefix;
        this.moduleName = NamingStrategy.capitalFirst(NamingStrategy.removePrefixAndCamel(tableName, tablePrefix)).toLowerCase();
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setServiceName("%sService");
        gc.setEntityName("%sEntity");
        mpg.setGlobalConfig(gc);


        dsc.setUrl("jdbc:mysql://" + generatorCodeDTO.getDataBaseUrl() + ":" + generatorCodeDTO.getDataBasePort() + "/" + generatorCodeDTO.getDataBaseName() + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(generatorCodeDTO.getUsername());
        dsc.setPassword(generatorCodeDTO.getPassword());
        mpg.setDataSource(dsc);

        pc.setModuleName(moduleName);
        pc.setParent(parentPackage);
        mpg.setPackageInfo(pc);


        loadStrategy();
    }


    protected void loadStrategy() {

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns(extendsFields);
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(tablePrefix);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    }


    protected void generateJava() {
        generateToCommon();
        generateToDataaccess();
        generateToCore();
        generateToWeb();
    }

    protected void generateToCommon() {
        generateDTO();
        generateQuery();
        generateConstant();
        generateErrorCodeEnum();
    }

    protected void generateToDataaccess() {
        generateEntity();
        generateMapper();
    }

    protected void generateToCore() {
        generateConvert();
        generateService();
        generateServiceImpl();
        generateLogic();
        generateLogicImpl();

        generateBasePojo();
        generateCommonQueryRequestBO();
        generateUpdateByIdRequest();
        generateModifyRequest();
        generateModifyResponse();
        generatePageQueryRequest();
        generatePageQueryResponse();
        generateQueryRequest();
        generateQueryResponse();
        generateListQueryRequest();
        generateListQueryResponse();
        generateSaveBatchRequest();
        generateSaveRequest();
        generateSaveResponse();
        generateUpdateResponse();
    }

    protected void generateToWeb() {
        generateController();
    }


    protected void generateXml() {
        generateMapperXml();
    }

    public void generateEntity() {

        String templatePath = "/templates/entity_customer.java.ftl";
        String filePath = pathForDataaccess + "/" + moduleName + "/entity/";
        String classBodySuffix = "Entity";
        String packageKey = "Entity";
        String subPackage = "Entity";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateMapper() {
        String templatePath = "/templates/mapper_customer.java.ftl";
        String filePath = pathForDataaccess + "/" + moduleName + "/mapper/";
        String classBodySuffix = "Mapper";
        String packageKey = "Mapper";
        String subPackage = "mapper";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);

    }

    public void generateService() {
        String templatePath = "/templates/service_customer.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/service/";
        String classBodySuffix = "Service";
        String packageKey = "Service";
        String subPackage = "service";
        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateServiceImpl() {
        String templatePath = "/templates/serviceImpl_customer.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/service/impl/";
        String classBodySuffix = "ServiceImpl";
        String packageKey = "ServiceImpl";
        String subPackage = "service.impl";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);

    }

    public void generateController() {
        String templatePath = "/templates/controller_customer.java.ftl";
        String filePath = pathForWeb + "/" + moduleName + "/controller/";
        String classBodySuffix = "Controller";
        String packageKey = "Controller";
        String subPackage = "controller";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateLogic() {
        String templatePath = "/templates/logic.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/logic/";
        String classBodySuffix = "Logic";
        String packageKey = "Logic";
        String subPackage = "logic";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateLogicImpl() {
        String templatePath = "/templates/logicImpl.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/logic/impl/";
        String classBodySuffix = "LogicImpl";
        String packageKey = "LogicImpl";
        String subPackage = "logic.impl";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateDTO() {
        // 自定义配置
        String templatePath = "/templates/dto.java.ftl";
        String filePath = pathForCommon + "/" + moduleName + "/pojo/dto/";
        String classBodySuffix = "DTO";
        String packageKey = "DTO";
        String subPackage = "pojo.dto";
        buildFieldFlag = true;
        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateQuery() {
        // 自定义配置
        String templatePath = "/templates/query.java.ftl";
        String filePath = pathForCommon + "/" + moduleName + "/pojo/query/";
        String classBodySuffix = "Query";
        String packageKey = "Query";
        String subPackage = "pojo.query";
        buildFieldFlag = true;
        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateConvert() {
        // 自定义配置
        String templatePath = "/templates/convert.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/convert/";
        String classBodySuffix = "Convert";
        String packageKey = "Convert";
        String subPackage = "convert";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateBasePojo() {
        // 自定义配置
        String templatePath = "/templates/domain.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/domain/";
        String classBodySuffix = "Domain";
        String packageKey = "Domain";
        String subPackage = "pojo.domain";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateCommonQueryRequestBO() {
        // 自定义配置
        String templatePath = "/templates/commonQueryRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/query/";
        String classBodySuffix = "CommonQueryRequestBO";
        String packageKey = "CommonQueryRequestBO";
        String subPackage = "pojo.bo.request.query";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateUpdateByIdRequest() {
        // 自定义配置
        String templatePath = "/templates/updateByIdRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/update/";
        String classBodySuffix = "UpdateByIdRequestBO";
        String packageKey = "UpdateByIdRequestBO";
        String subPackage = "pojo.bo.request.update";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateModifyRequest() {
        // 自定义配置
        String templatePath = "/templates/alterRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/";
        String classBodySuffix = "AlterRequestBO";
        String packageKey = "AlterRequestBO";
        String subPackage = "pojo.bo.request";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateModifyResponse() {
        // 自定义配置
        String templatePath = "/templates/alterResponseBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/response/";
        String classBodySuffix = "AlterResponseBO";
        String packageKey = "AlterResponseBO";
        String subPackage = "pojo.bo.response";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateListQueryRequest() {
        // 自定义配置
        String templatePath = "/templates/listQueryRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/query/";
        String classBodySuffix = "ListQueryRequestBO";
        String packageKey = "ListQueryRequestBO";
        String subPackage = "pojo.bo.request.query";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateListQueryResponse() {
        // 自定义配置
        String templatePath = "/templates/listQueryResponseBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/response/query/";
        String classBodySuffix = "ListQueryResponseBO";
        String packageKey = "ListQueryResponseBO";
        String subPackage = "pojo.bo.response.query";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generatePageQueryRequest() {
        // 自定义配置
        String templatePath = "/templates/pageQueryRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/query/";
        String classBodySuffix = "PageQueryRequestBO";
        String packageKey = "PageQueryRequestBO";
        String subPackage = "pojo.bo.request.query";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generatePageQueryResponse() {
        // 自定义配置
        String templatePath = "/templates/pageQueryResponseBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/response/query/";
        String classBodySuffix = "PageQueryResponseBO";
        String packageKey = "PageQueryResponseBO";
        String subPackage = "pojo.bo.response.query";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateQueryRequest() {
        // 自定义配置
        String templatePath = "/templates/queryRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/";
        String classBodySuffix = "QueryRequestBO";
        String packageKey = "QueryRequestBO";
        String subPackage = "pojo.bo.request";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateQueryResponse() {
        // 自定义配置
        String templatePath = "/templates/queryResponseBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/response/query/";
        String classBodySuffix = "QueryResponseBO";
        String packageKey = "QueryResponseBO";
        String subPackage = "pojo.bo.response.query";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateSaveBatchRequest() {
        // 自定义配置
        String templatePath = "/templates/saveBatchRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/save/";
        String classBodySuffix = "SaveBatchRequestBO";
        String packageKey = "SaveBatchRequestBO";
        String subPackage = "pojo.bo.request.save";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateSaveRequest() {
        // 自定义配置
        String templatePath = "/templates/saveRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/save/";
        String classBodySuffix = "SaveRequestBO";
        String packageKey = "SaveRequestBO";
        String subPackage = "pojo.bo.request.save";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateSaveResponse() {
        // 自定义配置
        String templatePath = "/templates/saveResponseBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/response/save/";
        String classBodySuffix = "SaveResponseBO";
        String packageKey = "SaveResponseBO";
        String subPackage = "pojo.bo.response.save";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateUpdateRequest() {
        // 自定义配置
        String templatePath = "/templates/updateRequestBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/request/update/";
        String classBodySuffix = "UpdateRequestBO";
        String packageKey = "UpdateRequestBO";
        String subPackage = "pojo.bo.request.update";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateUpdateResponse() {
        // 自定义配置
        String templatePath = "/templates/updateResponseBO.java.ftl";
        String filePath = pathForCore + "/" + moduleName + "/pojo/bo/response/update/";
        String classBodySuffix = "UpdateResponseBO";
        String packageKey = "UpdateResponseBO";
        String subPackage = "pojo.bo.response.update";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateConstant() {
        // 自定义配置
        String templatePath = "/templates/constant.java.ftl";
        String filePath = pathForCommon + "/" + moduleName + "/constant/";
        String classBodySuffix = "Constant";
        String packageKey = "Constant";
        String subPackage = "constant";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }

    public void generateErrorCodeEnum() {
        // 自定义配置
        String templatePath = "/templates/errorCodeEnum.java.ftl";
        String filePath = pathForCommon + "/" + moduleName + "/constant/";
        String classBodySuffix = "ErrorCodeEnum";
        String packageKey = "ErrorCodeEnum";
        String subPackage = "constant";

        generateCodeCoreForCustomer(templatePath, filePath, classBodySuffix, packageKey, subPackage);
    }


    public void generateMapperXml() {
        AutoGenerator mpg = copeAutoGenerator();
        // 自定义配置
        InjectionConfig cfg = getInjectionConfigDefault();
        List<FileOutConfig> focList = new ArrayList<>();
        FileOutConfig fileOutConfig = new FileOutConfig("/templates/mapper_customer.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + pathForXml + "/" + tableInfo.getName() + StringPool.DOT_XML;
            }
        };
        focList.add(fileOutConfig);
        cfg.setFileOutConfigList(focList);
        mpg.setStrategy(strategy);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
        buildConfigBuilderDefault(mpg, ConstVal.XML_PATH);
        mpg.execute();
    }

    protected void generateCodeCoreForDefault(String javaSrcPath, String retainKey) {
        AutoGenerator mpg = copeAutoGenerator();
        // 自定义配置
        InjectionConfig cfg = getInjectionConfigDefault();
        mpg.setCfg(cfg);
        gc.setOutputDir(projectPath + javaSrcPath);
        mpg.setGlobalConfig(gc);

        buildConfigBuilderDefault(mpg, retainKey);
        mpg.execute();
        buildFieldFlag = false;
    }

    protected void generateCodeCoreForCustomer(String templatePath, String filePath, String classBodySuffix, String packageKey, String subPackege) {
        AutoGenerator mpg = copeAutoGenerator();

        buildInjectionConfig(mpg, templatePath, filePath, classBodySuffix);
        buildConfigBuilderCustom(mpg, packageKey, subPackege);
        mpg.execute();
        buildFieldFlag = false;
    }

    protected void buildInjectionConfig(AutoGenerator mpg, String templatePath, String filePath, String classBodySuffix) {
        // 自定义配置
        InjectionConfig cfg = getInjectionConfigDefault();

        List<FileOutConfig> focList = getFileOutConfigForJava(templatePath, filePath, classBodySuffix);

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

    protected void buildConfigBuilderDefault(AutoGenerator mpg, String retainKey) {
        buildConfigBuilder(mpg, retainKey, null, null);
    }

    protected void buildConfigBuilderCustom(AutoGenerator mpg, String customPackageKey, String subPackage) {
        buildConfigBuilder(mpg, null, customPackageKey, subPackage);
    }

    /**
     * <p>
     * 简要说明
     * </p>
     *
     * @param mpg              代码生成器
     * @param customPackageKey : 包名的key
     * @param subPackage       : 子包名
     * @return void
     * @author daiqi
     * @date 2019/5/7 15:31
     */
    protected void buildConfigBuilder(AutoGenerator mpg, String retainKey, String customPackageKey, String subPackage) {
        ConfigBuilder configBuilder = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());

        TableInfo tableInfo = configBuilder.getTableInfoList().get(0);
        TableInfoExpand tableInfoExpand = expandTableInfo(tableInfo);
        tableInfoExpand.setModulePackage(generatorCodeDTO.getModulePackage());
        tableInfoExpand.setParentPackage(parentPackage);
        tableInfoExpand.setClassBodyName(getClassBody(tableInfoExpand));
        tableInfoExpand.setNameRemovePrefix(NamingStrategy.removePrefix(tableInfoExpand.getName(), tablePrefix));
        if (customPackageKey != null && subPackage != null) {
            if (!subPackage.startsWith(".")) {
                subPackage = "." + subPackage;
            }
            configBuilder.getPackageInfo().put(customPackageKey, pc.getParent() + subPackage);
        }
        if (this.mpg.getConfig() != null) {
            configBuilder.getPackageInfo().putAll(this.mpg.getConfig().getPackageInfo());
        }
//        {
//            "capitalName": "CertificateUploadStatus",
//                "columnType": "INTEGER",
//                "comment": "企业授权书上传状态[0:未上传,1:已上传]",
//                "convert": false,
//                "keyFlag": false,
//                "keyIdentityFlag": false,
//                "name": "certificate_upload_status",
//                "propertyName": "certificateUploadStatus",
//                "propertyType": "Integer",
//                "constant": "int(4)"
//        },
        if (buildFieldFlag) {
            buildFieldId(tableInfo);
        }
        List<TableInfo> list = new ArrayList<>();
        list.add(tableInfoExpand);
        configBuilder.setTableInfoList(list);
        retainKey(retainKey, configBuilder.getPathInfo());
        mpg.setConfig(configBuilder);
        this.mpg.setConfig(configBuilder);
    }

    protected void buildFieldId(TableInfo tableInfo) {
        TableField tableField = new TableField();
        tableField.setConvert(false);
        tableField.setKeyFlag(false);
        tableField.setKeyIdentityFlag(false);
        tableField.setColumnType(DbColumnType.LONG);
        tableField.setPropertyName((NamingStrategy.removePrefixAndCamel(tableInfo.getName(), strategy.getTablePrefix())) + "Id");
        tableField.setComment("数据表id");
        tableInfo.getFields().add(0, tableField);
    }

    protected InjectionConfig getInjectionConfigDefault() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> tempMap = new HashMap<>();
                tempMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                setMap(tempMap);
            }
        };
    }

    protected List<FileOutConfig> getFileOutConfigForJava(String templatePath, String filePath, String classBodySuffix) {
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                if (tableInfo instanceof TableInfoExpand) {
                    // 自定义输入文件名称
                    return projectPath + filePath + ((TableInfoExpand) tableInfo).getClassBodyName() + classBodySuffix + StringPool.DOT_JAVA;
                }
                return null;
            }
        });
        return focList;
    }

    protected AutoGenerator copeAutoGenerator() {
        AutoGenerator autoGenerator = new AutoGenerator();
        BeanUtils.copyProperties(mpg, autoGenerator);
        return autoGenerator;
    }

    protected String getClassBody(TableInfo tableInfo) {
        return NamingStrategy.capitalFirst(NamingStrategy.removePrefixAndCamel(tableInfo.getName(), strategy.getTablePrefix()));
    }

    protected TableInfoExpand expandTableInfo(TableInfo tableInfo) {
        TableInfoExpand tableInfoExpand = new TableInfoExpand();
        tableInfoExpand.setName(tableInfo.getName());
        for (String importPackages : tableInfo.getImportPackages()) {
            tableInfoExpand.setImportPackages(importPackages);
        }
        tableInfoExpand.setCommonFields(tableInfo.getCommonFields());
        tableInfoExpand.setComment(tableInfo.getComment());
        tableInfoExpand.setEntityName(tableInfo.getEntityName());
        tableInfoExpand.setMapperName(tableInfo.getMapperName());
        tableInfoExpand.setXmlName(tableInfo.getXmlName());
        tableInfoExpand.setServiceName(tableInfo.getServiceName());
        tableInfoExpand.setServiceImplName(tableInfo.getServiceImplName());
        tableInfoExpand.setControllerName(tableInfo.getControllerName());
        tableInfoExpand.setFields(tableInfo.getFields());

        return tableInfoExpand;
    }

    protected void retainKey(String key, Map<String, String> paramsMap) {
        String value = paramsMap.get(key);
        paramsMap.clear();
        if (key != null && value != null) {
            paramsMap.put(key, value);
        }
    }
}
