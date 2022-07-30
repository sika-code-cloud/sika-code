package com.sika.code.generator;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.sika.code.generator.dto.GeneratorClientDTO;
import com.sika.code.generator.dto.GeneratorDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/11 18:50
 */
@Slf4j
public class GeneratorCommander {

    //    jdbc:mysql://121.89.202.68:3306/lf-admin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    private static final String BASE_PACKAGE_PATH_PREFIX_FOR_JAVA = "src/main/java";
    private static final String BASE_PACKAGE_PATH_PREFIX_FOR_JAVA_TEST = "src/test/java";
    private static final String BASE_PACKAGE_PATH_PREFIX_FOR_XML = "src/main/resources";
    private static final String MODULE_PACKAGE = "{}.{}.business";
    private static final String DB_URL_TEMPLATE = "jdbc:mysql://{}:{}/{}?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    public static void doGenerator(GeneratorClientDTO clientDTO) {
        List<String> tableNames = clientDTO.getTableNames();
        for (String tableName : tableNames) {
            GeneratorDTO generatorDTO = new GeneratorDTO();
            generatorDTO.setInfrastructureCommonOutDir(buildFullPathForJava(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "infrastructure"), join(clientDTO.getProjectPrefix(), "infrastructure", "common")));
            generatorDTO.setInfrastructureParent(buildModulePackage(clientDTO, "infrastructure"));

            generatorDTO.setInfrastructureDbOutDir(buildFullPathForJava(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "infrastructure"), join(clientDTO.getProjectPrefix(), "infrastructure", "db")));
            generatorDTO.setInfrastructureDbParent(buildModulePackage(clientDTO, "infrastructure", "db"));
            generatorDTO.setMapperXmlOutDir(buildFullPathForXml(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "infrastructure"), join(clientDTO.getProjectPrefix(), "infrastructure", "db")));

            generatorDTO.setDomainOutDir(buildFullPathForJava(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "domain")));
            generatorDTO.setDomainParent(buildModulePackage(clientDTO, "domain"));

            generatorDTO.setApplicationOutDir(buildFullPathForJava(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "application")));
            generatorDTO.setApplicationParent(buildModulePackage(clientDTO, "application"));

            generatorDTO.setInterfacesRestOutDir(buildFullPathForJava(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "interfaces")));
            generatorDTO.setInterfacesRestParent(buildModulePackage(clientDTO, "interfaces"));

            generatorDTO.setTestDomainOutDir(buildFullPathForJavaTest(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "interfaces")));
            generatorDTO.setTestDomainParent(buildModulePackage(clientDTO, "interfaces"));

            generatorDTO.setTestRestOutDir(buildFullPathForJavaTest(clientDTO, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), "interfaces")));
            generatorDTO.setTestRestParent(buildModulePackage(clientDTO, "interfaces"));

            generatorDTO.setAuthor(clientDTO.getAuthor());

            generatorDTO.setTableName(tableName);
            generatorDTO.setDbPassword(clientDTO.getDbPassword());
            generatorDTO.setDbUsername(clientDTO.getDbUsername());
            generatorDTO.setDbUrl(StrUtil.format(DB_URL_TEMPLATE, clientDTO.getIp(), clientDTO.getPort(), clientDTO.getDbName()));
            generatorDTO.setTablePrefix(clientDTO.getTablePrefix());

            generatorDTO.setGenerateController(clientDTO.isGenerateController());
            generatorDTO.setGenerateDomain(clientDTO.isGenerateDomain());
            generatorDTO.setApplicationClassName(clientDTO.getApplicationClassName());
            generatorDTO.setApplicationSimpleName(clientDTO.getApplicationSimpleName());

            new GeneratorExecutor(generatorDTO).generator();
        }
    }

    private static String buildFullPathForJava(GeneratorClientDTO clientDTO, String... subProjectNames) {
        StringBuilder stringBuilder = buildProjectPath(clientDTO, BASE_PACKAGE_PATH_PREFIX_FOR_JAVA, subProjectNames);
//        StringBuilder stringBuilder = buildFullPathCore(projectSuffix, basePackagePathPrefixForJava);
        log.info(subProjectNames + "的全路径为：" + stringBuilder);
        return stringBuilder.toString();
    }

    private static String buildFullPathForJavaTest(GeneratorClientDTO clientDTO, String... subProjectNames) {
        StringBuilder stringBuilder = buildProjectPath(clientDTO, BASE_PACKAGE_PATH_PREFIX_FOR_JAVA_TEST, subProjectNames);
//        StringBuilder stringBuilder = buildFullPathCore(projectSuffix, basePackagePathPrefixForJavaTest);
        log.info(subProjectNames + "的全路径为：" + stringBuilder);
        return stringBuilder.toString();
    }

    private static String buildFullPathForXml(GeneratorClientDTO clientDTO, String... subProjectNames) {
        StringBuilder stringBuilder = buildProjectPath(clientDTO, BASE_PACKAGE_PATH_PREFIX_FOR_XML, subProjectNames);
//        StringBuilder stringBuilder = buildFullPathCore(projectSuffix, basePackagePathPrefixForXml);
        log.info(subProjectNames + "的全路径为：" + stringBuilder);
        return stringBuilder.toString();
    }

    protected static StringBuilder buildFullPathCore(GeneratorClientDTO clientDTO, String projectSuffix, String basePackagePathPrefix) {
        return buildProjectPath(clientDTO, basePackagePathPrefix, clientDTO.getProjectPrefix(), join(clientDTO.getProjectPrefix(), projectSuffix));
    }

    private static String join(Object... names) {
        return StrUtil.join("-", names);
    }

    private static StringBuilder buildProjectPath(GeneratorClientDTO clientDTO, String basePackagePathPrefix, String... subModuleNames) {
        StringBuilder stringBuilder = new StringBuilder(System.getProperty("user.dir")).append("/").append(clientDTO.getProjectPrefix());
        System.out.println("----" + stringBuilder.toString());
        if (ArrayUtil.isNotEmpty(subModuleNames)) {
            for (int i = 0; i < subModuleNames.length; ++i) {
                String subModuleName = subModuleNames[i];
                if (subModuleName.equals(clientDTO.getProjectPrefix()) && i == 0) {
                    continue;
                }
                stringBuilder.append("/").append(subModuleName);
            }
        }
        stringBuilder.append("/").append(basePackagePathPrefix);
        System.out.println("======" + stringBuilder);
        return stringBuilder;
    }

    protected static String buildModulePackage(GeneratorClientDTO clientDTO, String application, String... subPackages) {
        String businessPre = application;
        if (ArrayUtil.isNotEmpty(subPackages)) {
            businessPre = StrUtil.join(".", businessPre, subPackages);
        }
        String packageName = StrUtil.format(MODULE_PACKAGE, clientDTO.getModulePackagePrefix(), businessPre);

        log.info("{}包名为:{}", application, packageName);
        return packageName;
    }
}
