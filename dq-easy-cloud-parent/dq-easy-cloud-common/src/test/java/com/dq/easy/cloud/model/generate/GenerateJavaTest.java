package com.dq.easy.cloud.model.generate;

import org.junit.Before;
import org.junit.Test;

import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaBOBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaControllerBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaDAOImplBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaDOBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaDTOBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaLogicImplBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaQueryBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaServiceImplBO;
import com.dq.easy.cloud.model.generate.bo.javaclass.DqGenerateJavaVOBO;
import com.dq.easy.cloud.model.generate.bo.javaenum.DqGenerateJavaErrorCodeBO;
import com.dq.easy.cloud.model.generate.bo.javainf.DqGenerateJavaDAOBO;
import com.dq.easy.cloud.model.generate.bo.javainf.DqGenerateJavaJpaDAOBO;
import com.dq.easy.cloud.model.generate.bo.javainf.DqGenerateJavaLogicBO;
import com.dq.easy.cloud.model.generate.bo.javainf.DqGenerateJavaServiceBO;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.mysql.DqDataBaseMysqlConfig;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql.DqMysqlDataSources;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.java.constant.DqCodeGenerateJavaConstant.DqSubModuleDefaultPackageName;
import com.dq.easy.cloud.module.common.generator.code.java.pojo.dto.DqGenerateJavaBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.java.rule.DqGenerateJavaClassRule;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo.mybatis.DqGenerateXmlMybatisBO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisDTO;

/** 生成java代码测试类 */
public class GenerateJavaTest {
	// 数据库配置信息
	private DqDatabaseAbstactConfig databaseAbstactConfig;
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
		databaseAbstactConfig = new DqDataBaseMysqlConfig();
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
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_PO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, true);
		generateJavaBaseDTO.setCoverSwith(true);
		try {
			new DqGenerateJavaDOBO(generateJavaBaseDTO, generateRule)
					.buildDatabaseDataSources(new DqMysqlDataSources(databaseAbstactConfig)).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDTOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_DTO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaDTOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaBOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_BO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, true);
		try {
			new DqGenerateJavaBOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaQueryByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_QUERY;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaQueryBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaVOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.POJO_VO;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			DqGenerateJavaVOBO generateJavaVOBO = new DqGenerateJavaVOBO(generateJavaBaseDTO, 
					generateRule);
			generateJavaVOBO.buildDatabaseDataSources(new DqMysqlDataSources(databaseAbstactConfig)).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDAOByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.DAO_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaDAOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 生成mybatis映射文件 */
	@Test
	public void generateMybatis(){
		// 获取DAO处理类的完整名称		
		String daoSubModulePackageName = DqSubModuleDefaultPackageName.DAO_INF;
		DqGenerateJavaBaseDTO daoDto = new DqGenerateJavaBaseDTO(projectNameDao, basePackageName, moduleName,
				daoSubModulePackageName, classBodyName, classComment);
		String fullPackageNameDAO = daoDto.buildFullPackageName();
		String simpleClassTypeDAO = classBodyName + DqClassNameEndWith.DAO_INF;
		String fullClsssTypeDAO = DqCodeGenerateUtils.getFullClassType(fullPackageNameDAO, simpleClassTypeDAO);

		DqGenerateXmlMybatisDTO mybatisDTO = new DqGenerateXmlMybatisDTO();
		mybatisDTO.setCoverSwith(true);
		mybatisDTO.setNamespace(fullClsssTypeDAO);
		mybatisDTO.setTableName(tableName);
		mybatisDTO.setProjectName(projectNameMybatis);
		mybatisDTO.setSubPath("mybatis");

		// 获取DO的完整路径
		String subModulePackageNameDO = DqSubModuleDefaultPackageName.POJO_DO;
		DqGenerateJavaBaseDTO dtoDO = new DqGenerateJavaBaseDTO(projectNamePojo, basePackageName, moduleName,
				subModulePackageNameDO, classBodyName, classComment);
		String fullPackageNameDO = dtoDO.buildFullPackageName();
		String simpleClassTypeDO = classBodyName + DqClassNameEndWith.POJO_DO;
		String fullClsssTypeDO = DqCodeGenerateUtils.getFullClassType(fullPackageNameDO, simpleClassTypeDO);

		mybatisDTO.setSimpleClassTypeDO(simpleClassTypeDO);
		mybatisDTO.setFullClassTypeDO(fullClsssTypeDO);
		mybatisDTO.setMappersConfigName("sqlmap-config");

		DqGenerateXmlMybatisBO mybatisBO = new DqGenerateXmlMybatisBO(mybatisDTO);
		mybatisBO.initData();
		mybatisBO.buildDatabaseDataSources(new DqMysqlDataSources(databaseAbstactConfig));

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
		String subModulePackageName = DqSubModuleDefaultPackageName.DAO_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaJpaDAOBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaDAOImplByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.DAO_IMPL;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameDao, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaDAOImplBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaServiceByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.SERVICE_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaServiceBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaServiceImplByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.SERVICE_IMPL;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaServiceImplBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaLogicByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.LOGIC_INF;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaLogicBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaLogicImplByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.LOGIC_IMPL;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameService, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaLogicImplBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaControllerByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.CONTROLLER;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameController, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, false, false, false);
		try {
			new DqGenerateJavaControllerBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateJavaErrorCodeByDataBase() {
		String subModulePackageName = DqSubModuleDefaultPackageName.ERROR_CODE;
		DqGenerateJavaBaseDTO generateJavaBaseDTO = new DqGenerateJavaBaseDTO(projectNameController, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		generateJavaBaseDTO.setCoverSwith(true);
		DqGenerateRule generateRule = new DqGenerateJavaClassRule(true, true, true, false);
		try {
			new DqGenerateJavaErrorCodeBO(generateJavaBaseDTO, generateRule).generateCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
