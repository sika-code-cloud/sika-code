package com.sika.code.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.generator.constant.GenerratorClassEnum;
import com.sika.code.generator.dto.GeneratorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器执行器
 *
 * @author daiqi
 * @create 2021-10-29 0:57
 */
@Slf4j
public class GeneratorExecutor {
    private final GeneratorDTO generatorDTO;

    public GeneratorExecutor(GeneratorDTO generatorDTO) {
        this.generatorDTO = generatorDTO;
    }

    private List<TemplateType> getDisableTypes(List<TemplateType> needGenerateTemplateTypes) {
        if (CollUtil.isEmpty(needGenerateTemplateTypes)) {
            return CollUtil.newArrayList(TemplateType.values());
        }
        List<TemplateType> templateTypesForRet = Lists.newArrayList();
        for (TemplateType templateType : TemplateType.values()) {
            if (needGenerateTemplateTypes.contains(templateType)) {
                continue;
            }
            templateTypesForRet.add(templateType);
        }
        return templateTypesForRet;
    }

    /**
     * 数据源配置
     */
    private DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig.Builder(
                generatorDTO.getDbUrl(),
                generatorDTO.getDbUsername(),
                generatorDTO.getDbPassword())
                .build();
    }

    /**
     * 策略配置
     */
    private StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder().addInclude(generatorDTO.getTableName()); // 设置需要生成的表名
    }

    /**
     * 全局配置
     */
    private GlobalConfig.Builder globalConfig() {
        GlobalConfig.Builder builder = new GlobalConfig.Builder()
                .author(generatorDTO.getAuthor())
                .disableOpenDir()
                .commentDate("yyyy-MM-dd HH:mm:ss");
        if (generatorDTO.getFileOverride()) {
            builder.fileOverride();
        }
        return builder;
    }

    /**
     * 包配置
     */
    private PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder();
    }

    /**
     * 模板配置
     */
    private TemplateConfig.Builder templateConfig() {
        return new TemplateConfig.Builder().disable();
    }

    /**
     * 注入配置
     */
    private InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.toString());
        });
    }

    public void generator() {
        generateInfrastructureCommon();
        generateInfrastructureDb();
        generateDomain();
        generateApplication();
        generateController();
        generateTest();
        generateXml();
    }

    private void generateInfrastructureCommon() {
        generatorDTO();
        generatorQuery();
    }

    private void generateInfrastructureDb() {
        generatorMapperNew();
        generatorPO();
    }

    private void generateDomain() {
        generatorRepository();
        generatorRepositoryImpl();
//        generatorConvert();

        if (generatorDTO.getGenerateDomain()) {
            generateFactory();
            generateContext();
            generateAggregateRoot();
            generateEntity();
        }
    }

    private void generateApplication() {
        generatorService();
        generatorServiceImpl();
    }

    private void generateController() {
        if (generatorDTO.getGenerateController()) {
            generatorController();
        }
    }

    private void generateTest() {
        generateRepositoryTest();
        generateServiceTest();
        if (generatorDTO.getGenerateController()) {
            generateControllerTest();
        }
    }

    private void generatorController() {
        generatorCore("Controller",
                "controller.java",
                buildPackage(getEntityName(), "controller"),
                generatorDTO.getInterfacesRestOutDir(),
                generatorDTO.getInterfacesRestParent());
    }

    private void generatorService() {
        generatorCore("Service",
                "service.java",
                buildPackage(getEntityName(), "service"),
                generatorDTO.getApplicationOutDir(),
                generatorDTO.getApplicationParent());
    }

    private void generateFactory() {
        generatorCore("Factory",
                "factory.java",
                buildPackage(getEntityName(), "domain.factory"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generateContext() {
        generatorCore("Context",
                "context.java",
                buildPackage(getEntityName(), "domain.context"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generateAggregateRoot() {
        generatorCore("AggregateRoot",
                "aggregateRoot.java",
                buildPackage(getEntityName(), "domain.aggregate"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generateEntity() {
        generatorCore("Entity",
                "entity.java",
                buildPackage(getEntityName(), "domain.entity"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generatorServiceImpl() {
        generatorCore("ServiceImpl",
                "serviceImpl.java",
                buildPackage(getEntityName(), "service.impl"),
                generatorDTO.getApplicationOutDir(),
                generatorDTO.getApplicationParent());
    }

    private void generatorRepository() {
        generatorCore("Repository",
                "repository.java",
                buildPackage(getEntityName(), "repository"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generatorRepositoryImpl() {
        generatorCore("RepositoryImpl",
                "repositoryImpl.java",
                buildPackage(getEntityName(), "repository.impl"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generatorMapperNew() {
        String [] ignoreColumns = generatorDTO.getIgnoreColumns();
        String [] tempIg = {""};
        generatorDTO.setIgnoreColumns(tempIg);
        generatorCore("Mapper",
                "mapper.java",
                buildPackage(getEntityName(), "mapper"),
                generatorDTO.getInfrastructureDbOutDir(),
                generatorDTO.getInfrastructureDbParent());
        generatorDTO.setIgnoreColumns(ignoreColumns);
    }

    private void generatorConvert() {
        generatorCore("Converter",
                "converter.java",
                buildPackage(getEntityName(), "converter"),
                generatorDTO.getDomainOutDir(),
                generatorDTO.getDomainParent());
    }

    private void generatorQuery() {
        generatorCore("Query",
                "query.java",
                buildPackage(getEntityName(), "pojo.query"),
                generatorDTO.getInfrastructureCommonOutDir(),
                generatorDTO.getInfrastructureParent());
    }

    private void generatorDTO() {
        generatorCore("DTO",
                "dto.java",
                buildPackage(getEntityName(), "pojo.dto"),
                generatorDTO.getInfrastructureCommonOutDir(),
                generatorDTO.getInfrastructureParent());
    }

    private void generatorPO() {
        generatorCore("",
                "po.java",
                buildPackage(getEntityName(), "po"),
                generatorDTO.getInfrastructureDbOutDir(),
                generatorDTO.getInfrastructureDbParent());
    }

    private void generateRepositoryTest() {
        generatorCore("Repository",
                "Test",
                "repository.test.java",
                buildPackage(getEntityName(), "repository"),
                generatorDTO.getTestDomainOutDir(),
                generatorDTO.getTestDomainParent());
    }

    private void generateServiceTest() {
        generatorCore("Service",
                "Test",
                "service.test.java",
                buildPackage(getEntityName(), "service"),
                generatorDTO.getTestDomainOutDir(),
                generatorDTO.getTestDomainParent());
    }

    private void generateControllerTest() {
        generatorCore("Controller",
                "Test",
                "controller.test.java",
                buildPackage(getEntityName(), "controller"),
                generatorDTO.getTestRestOutDir(),
                generatorDTO.getTestDomainParent());
    }

    private void generatorCore(String objSuffix, String templatePath, String entity, String outDir, String parent) {
        generatorCore(objSuffix, StrUtil.EMPTY, templatePath, entity, outDir, parent);
    }


    private void generatorCore(String objSuffix, String objPrefix, String templatePath, String entity, String outDir, String parent) {
        List<TemplateType> templateTypes = getDisableTypes(Lists.newArrayList(TemplateType.ENTITY));
        String moduleName = getEntityName().toLowerCase();
        GenerateJavaParam generateJavaParam = new GenerateJavaParam()
                .setModuleName(moduleName)
                .setEntityBodyName(getEntityName())
                .setObjPrefix(objPrefix)
                .setObjSuffix(objSuffix)
                .setTemplateTypes(templateTypes)
                .setTemplatePath("/template/" + templatePath)
                .setEntity(entity)
                .setOutDir(outDir)
                .setParent(parent);
        System.out.println("构建的java参数为：" + JSONUtil.toJSONString(generateJavaParam));
        generateCoreNew(generateJavaParam);
    }

    private String buildPackage(Object... params) {
        return StrUtil.join(StrPool.DOT, params).toLowerCase();
    }

    private void generateCoreNew(GenerateJavaParam generateJavaParam) {
        String templatePath = generateJavaParam.getTemplatePath();
        String tablePrefix = generatorDTO.getTablePrefix();
        String objSuffix = generateJavaParam.getObjSuffix();
        String objPrefix = generateJavaParam.getObjPrefix();
        String superClass = generateJavaParam.getSuperClass();
        String outDir = generateJavaParam.getOutDir();
        String parent = generateJavaParam.getParent();
        String entity = generateJavaParam.getEntity();
        String moduleName = generateJavaParam.getModuleName();
        List<TemplateType> templateTypes = generateJavaParam.getTemplateTypes();

        // 数据源配置
        AutoGenerator generator = new AutoGenerator(getDataSourceConfig());

        // 设置全局配置
        GlobalConfig globalConfig = globalConfig().outputDir(outDir).build();
        generator.global(globalConfig);

        // 包名配置
        PackageConfig packageConfig = packageConfig()
                .parent(parent)
                .entity(entity)
                .build();
        generator.packageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategyConfig = strategyConfig().addTablePrefix(tablePrefix)
                .entityBuilder()
                .enableLombok()
                .formatFileName(objPrefix + "%s" + objSuffix)
                .disableSerialVersionUID()
                .superClass(superClass)
                .addIgnoreColumns(generatorDTO.getIgnoreColumns()).build(); // 基于数据库字段
        generator.strategy(strategyConfig);

        // 类模板信息配置
        generator.template(templateConfig()
                .entity(templatePath)
                .disable(ArrayUtil.toArray(templateTypes, TemplateType.class))
                .build());

        Map<String, Object> map = buildCustomMap(moduleName, packageConfig, generateJavaParam);
        Map<String, String> customFile = new HashMap<>();
        // 拦截器配置
        generator.injection(injectionConfig().customMap(map).customFile(customFile).build());
        // 执行
        generator.execute(new FreemarkerTemplateEngine());
    }

    protected Map<String, Object> buildCustomMap(String moduleName, PackageConfig packageConfig, GenerateJavaParam generateJavaParam) {
        Map<String, Object> map = new HashMap<>();
        List<String> extendPkg = new ArrayList<>();
        map.put("sikaPackage", getCustomPackage(moduleName, packageConfig));
        map.put("extendPkg", extendPkg);
        map.put("sikaPrimaryType", generatorDTO.getPrimaryKeyClass().getSimpleName());
        map.put("sikaEntityBodyName", generateJavaParam.getEntityBodyName());
        map.put("sikaApplicationClassName", generatorDTO.getApplicationClassName());
        map.put("sikaApplicationSimpleName", generatorDTO.getApplicationSimpleName());
        return map;
    }

    protected Map<String, String> getCustomPackage(String moduleName, PackageConfig packageConfig) {
        Map<String, String> customPackage = Maps.newHashMap();
        customPackage.putAll(packageConfig.getPackageInfo());
        customPackage.put(ConstVal.MAPPER, getMapperPackage(moduleName));
        customPackage.put("PO", getPoPackage(moduleName));
        customPackage.put("Query", getQueryPackage(moduleName));
        customPackage.put("Entity", getPoPackage(moduleName));
        customPackage.put("DTO", getDtoPackage(moduleName));
        customPackage.put("Repository", getRepositoryPackage(moduleName));
        customPackage.put("Service", getServicePackage(moduleName));
        customPackage.put("ApiService", getRpcApiServicePackage(moduleName));
        customPackage.put("ApiServiceImpl", getRpcImpServicePackage(moduleName));
        return customPackage;
    }

    private void generateXml() {
        if (generatorDTO.getIgnoreClass().contains(GenerratorClassEnum.XML)) {
            return;
        }
        String entityBodyName = getEntityName();
        String tablePrefix = generatorDTO.getTablePrefix();
        String infrastructureXmlOutDir = generatorDTO.getMapperXmlOutDir();
        List<TemplateType> templateTypes = getDisableTypes(Lists.newArrayList(TemplateType.XML));
        AutoGenerator generator = new AutoGenerator(getDataSourceConfig());
        StrategyConfig strategyConfig = strategyConfig().addTablePrefix(tablePrefix)
                .mapperBuilder()
                .formatXmlFileName(entityBodyName)
                .enableBaseResultMap()
                .enableBaseColumnList().build(); // 基于数据库字段
        GlobalConfig globalConfig = globalConfig().outputDir(infrastructureXmlOutDir).build();
        generator.global(globalConfig);
        Map<OutputFile, String> pathInfo = Maps.newHashMap();
        pathInfo.put(OutputFile.mapperXml, infrastructureXmlOutDir + "/mapper");
        String moduleName = entityBodyName.toLowerCase();
        log.info("moduleName:{}", moduleName);

        PackageConfig packageConfig = packageConfig()
                .pathInfo(pathInfo)
                .xml("mapper").build();
        generator.packageInfo(packageConfig);

        generator.strategy(strategyConfig);
        generator.template(templateConfig()
                .mapperXml("/template/mapper.xml")
                .disable(ArrayUtil.toArray(templateTypes, TemplateType.class))
                .build());

        GenerateJavaParam generateJavaParam = new GenerateJavaParam();
        generateJavaParam.setEntityBodyName(entityBodyName);
        Map<String, Object> map = buildCustomMap(moduleName, packageConfig, generateJavaParam);
        Map<String, String> customFile = new HashMap<>();
        generator.injection(injectionConfig().customMap(map).customFile(customFile).build());
        generator.execute(new FreemarkerTemplateEngine());
    }

    private String getEntityName() {
        String removePrefix = StrUtil.removePrefix(generatorDTO.getTableName(), generatorDTO.getTablePrefix());
        String removeSuffix = StrUtil.removeSuffix(removePrefix, generatorDTO.getTableSuffix());
        return StrUtil.upperFirst(NamingStrategy.underlineToCamel(removeSuffix));
    }


    private String getMapperPackage(String moduleName) {
        return buildPackage(generatorDTO.getInfrastructureDbParent(), moduleName, "mapper");
    }

    private String getQueryPackage(String moduleName) {
        return buildPackage(generatorDTO.getInfrastructureParent(), moduleName, "pojo", "query");
    }

    private String getPoPackage(String moduleName) {
        return buildPackage(generatorDTO.getInfrastructureDbParent(), moduleName, "po");
    }

    private String getDtoPackage(String moduleName) {
        return buildPackage(generatorDTO.getInfrastructureParent(), moduleName, "pojo", "dto");
    }

    private String getRepositoryPackage(String moduleName) {
        return buildPackage(generatorDTO.getDomainParent(), moduleName, "repository");
    }

    private String getServicePackage(String moduleName) {
        return buildPackage(generatorDTO.getApplicationParent(), moduleName, "service");
    }

    private String getRpcApiServicePackage(String moduleName) {
        return generatorDTO.getInterfacesRpcApiParent();
    }

    private String getRpcImpServicePackage(String moduleName) {
        return generatorDTO.getInterfacesRpcImplParent();
    }

    @Data
    @Accessors(chain = true)
    public static class GenerateJavaParam {
        String objPrefix = StrUtil.EMPTY;
        String objSuffix = StrUtil.EMPTY;
        String moduleSubName = StrUtil.EMPTY;
        List<TemplateType> templateTypes;
        String templatePath = StrUtil.EMPTY;
        String outDir;
        String parent = StrUtil.EMPTY;
        String superClass;
        String entity;
        String entityBodyName;
        String moduleName;
        boolean notNeedModulePackage;
        boolean notPackage;
    }
}
