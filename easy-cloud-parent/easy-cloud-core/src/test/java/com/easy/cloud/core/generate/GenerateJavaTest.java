package com.easy.cloud.core.generate;

import org.junit.Before;
import org.junit.Test;

import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.common.generator.code.base.config.database.mysql.EcDataBaseMysqlConfig;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.mysql.EcMysqlDataSources;
import com.easy.cloud.core.common.generator.code.base.utils.EcCodeGenerateUtils;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.common.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.common.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;
import com.easy.cloud.core.common.generator.code.java.rule.EcGenerateJavaClassRule;
import com.easy.cloud.core.common.generator.code.xml.pojo.bo.mybatis.EcGenerateXmlMybatisBO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisDTO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaBOBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaControllerBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaDAOImplBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaDOBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaDTOBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaLogicImplBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaQueryBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaServiceImplBO;
import com.easy.cloud.core.generate.bo.javaclass.EcGenerateJavaVOBO;
import com.easy.cloud.core.generate.bo.javaenum.EcGenerateJavaErrorCodeBO;
import com.easy.cloud.core.generate.bo.javainf.EcGenerateJavaDAOBO;
import com.easy.cloud.core.generate.bo.javainf.EcGenerateJavaJpaDAOBO;
import com.easy.cloud.core.generate.bo.javainf.EcGenerateJavaLogicBO;
import com.easy.cloud.core.generate.bo.javainf.EcGenerateJavaServiceBO;

/** 生成java代码测试类 */
public class GenerateJavaTest {
	// 数据库配置信息
	private EcDatabaseAbstactConfig databaseAbstactConfig;
	// pojo类所在的项目名称
	private String projectNamePojo = "dq-easy-cloud-common";
	// dao类所在的项目名称
	private String projectNameDao = "dq-easy-cloud-common";
	// service类所在的项目名称
	private String projectNameService = "dq-easy-cloud-common";
	// controller类所在的项目名称
	private String projectNameController = "dq-easy-cloud-common";
	// mybatis配置文件所在的项目名称
	private String projectNameMybatis = "dq-easy-cloud-common";
	// 基础包名称
	private String basePackageName = "com.dq.easy";

	// 表名
	private String tableName = "easy_user_info";
	// 模块包名
	private String moduleName = "user1";
	// 类主体名称
	private String classBodyName = "User";
	// 类的注释
	private String classComment = "用户";

	@Before
	public void initData() {
		databaseAbstactConfig = new EcDataBaseMysqlConfig();
		databaseAbstactConfig.buildDatabaseBaseUrl("jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com");
		databaseAbstactConfig.buildDatabasePort("3306");
		databaseAbstactConfig.buildDatabaseName("dq_easy_cloud");
		databaseAbstactConfig.buildDatabaseUserName("dq_easy_cloud");
		databaseAbstactConfig.buildDatabasePassword("dq_easy_cloud123");
		databaseAbstactConfig.buildTableName(tableName);
	}

	/** 代码生成组建测试 */
	@Test
	public void testGenerateJavaCode() {
		// 生成数据持久化类
		generateJavaDOByDataBase();
		// 生成数据传输类
		generateJavaDTOByDataBase();
		// 生成业务逻辑类
		generateJavaBOByDataBase();
		// 生成查询类
		generateJavaQueryByDataBase();
		// 生成视图类
		generateJavaVOByDataBase();
		// 生成DAO类
//		generateJavaDAOByDataBase();
		// 生成mybatis文件		
		generateMybatis();
		// 生成DAO类
		 generateJavaJpaDAOByDataBase();
		// 生成DAO实现类
		generateJavaDAOImplByDataBase();
		// 生成Service类
		generateJavaServiceByDataBase();
		// 生成Service实现类
		generateJavaServiceImplByDataBase();
		// 生成Logic接口类
		generateJavaLogicByDataBase();
		// 生成Logic实现类
		generateJavaLogicImplByDataBase();
		// 生成Controller类
		generateJavaControllerByDataBase();
		// 生成错误代码类
		generateJavaErrorCodeByDataBase();
	}

	@Test
	public void generateJavaDOByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_PO;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, true);
		generateJavaBaseDTO.setCoverSwith(true);
		try {
			new EcGenerateJavaDOBO(generateJavaBaseDTO, generateRule)
					.buildDatabaseDataSources(new EcMysqlDataSources(databaseAbstactConfig)).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDTOByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_DTO;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, false, false, false);
		try {
			new EcGenerateJavaDTOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaBOByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_BO;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, false, false, true);
		try {
			new EcGenerateJavaBOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaQueryByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_QUERY;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			new EcGenerateJavaQueryBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaVOByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.POJO_VO;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			EcGenerateJavaVOBO generateJavaVOBO = new EcGenerateJavaVOBO(generateJavaBaseDTO, 
					generateRule);
			generateJavaVOBO.buildDatabaseDataSources(new EcMysqlDataSources(databaseAbstactConfig)).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDAOByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			new EcGenerateJavaDAOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 生成mybatis映射文件 */
	@Test
	public void generateMybatis(){
		// 获取DAO处理类的完整名称		
		String daoSubModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
		EcGenerateJavaBaseDTO daoDto = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName, moduleName,
				daoSubModulePackageName, classBodyName, classComment);
		String fullPackageNameDAO = daoDto.buildFullPackageName();
		String simpleClassTypeDAO = classBodyName + EcClassNameEndWith.DAO_INF;
		String fullClsssTypeDAO = EcCodeGenerateUtils.getFullClassType(fullPackageNameDAO, simpleClassTypeDAO);

		EcGenerateXmlMybatisDTO mybatisDTO = new EcGenerateXmlMybatisDTO();
		mybatisDTO.setCoverSwith(true);
		mybatisDTO.setNamespace(fullClsssTypeDAO);
		mybatisDTO.setTableName(tableName);
		mybatisDTO.setProjectName(projectNameMybatis);
		mybatisDTO.setSubPath("mybatis");

		// 获取DO的完整路径
		String subModulePackageNameDO = EcSubModuleDefaultPackageName.POJO_DO;
		EcGenerateJavaBaseDTO dtoDO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName, moduleName,
				subModulePackageNameDO, classBodyName, classComment);
		String fullPackageNameDO = dtoDO.buildFullPackageName();
		String simpleClassTypeDO = classBodyName + EcClassNameEndWith.POJO_DO;
		String fullClsssTypeDO = EcCodeGenerateUtils.getFullClassType(fullPackageNameDO, simpleClassTypeDO);

		mybatisDTO.setSimpleClassTypeDO(simpleClassTypeDO);
		mybatisDTO.setFullClassTypeDO(fullClsssTypeDO);
		mybatisDTO.setMappersConfigName("sqlmap-config");

		EcGenerateXmlMybatisBO mybatisBO = new EcGenerateXmlMybatisBO(mybatisDTO);
		mybatisBO.initData();
		mybatisBO.buildDatabaseDataSources(new EcMysqlDataSources(databaseAbstactConfig));

		mybatisBO.buildRootContentElementDesc();
		mybatisBO.buildResultMap();
		mybatisBO.buildColumnList();
		mybatisBO.buildFindById();
		mybatisBO.buildListCount();
		mybatisBO.buildListPage();
		mybatisBO.buildSetColumnSql();
		mybatisBO.buildSave();
		mybatisBO.buildUpdate();
		try {
			mybatisBO.generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaJpaDAOByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			new EcGenerateJavaJpaDAOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDAOImplByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.DAO_IMPL;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			new EcGenerateJavaDAOImplBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaServiceByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.SERVICE_INF;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			new EcGenerateJavaServiceBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaServiceImplByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.SERVICE_IMPL;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, false, false, false);
		try {
			new EcGenerateJavaServiceImplBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaLogicByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.LOGIC_INF;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, false, false, false);
		try {
			new EcGenerateJavaLogicBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaLogicImplByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.LOGIC_IMPL;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, false, false, false);
		try {
			new EcGenerateJavaLogicImplBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaControllerByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.CONTROLLER;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameController, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, false, false, false);
		try {
			new EcGenerateJavaControllerBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaErrorCodeByDataBase() {
		String subModulePackageName = EcSubModuleDefaultPackageName.ERROR_CODE;
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNameController, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			new EcGenerateJavaErrorCodeBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
