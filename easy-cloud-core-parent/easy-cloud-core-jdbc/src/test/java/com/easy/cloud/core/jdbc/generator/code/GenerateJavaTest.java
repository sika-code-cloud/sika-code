package com.easy.cloud.core.jdbc.generator.code;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.easy.cloud.core.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.generator.code.base.config.database.mysql.EcDataBaseMysqlConfig;
import com.easy.cloud.core.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.generator.code.base.sources.database.mysql.EcMysqlDataSources;
import com.easy.cloud.core.generator.code.base.utils.EcCodeGenerateUtils;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaBOBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaControllerBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaDAOImplBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaDOBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaDTOBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaLogicImplBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaQueryBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaServiceImplBO;
import com.easy.cloud.core.generator.code.bo.javaclass.EcGenerateJavaVOBO;
import com.easy.cloud.core.generator.code.bo.javaenum.EcGenerateJavaErrorCodeBO;
import com.easy.cloud.core.generator.code.bo.javainf.EcGenerateJavaDAOBO;
import com.easy.cloud.core.generator.code.bo.javainf.EcGenerateJavaJpaDAOBO;
import com.easy.cloud.core.generator.code.bo.javainf.EcGenerateJavaLogicBO;
import com.easy.cloud.core.generator.code.bo.javainf.EcGenerateJavaServiceBO;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcClassNameEndWith;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcIgnoreField;
import com.easy.cloud.core.generator.code.java.constant.EcCodeGenerateJavaConstant.EcSubModuleDefaultPackageName;
import com.easy.cloud.core.generator.code.java.pojo.dto.EcGenerateJavaBaseDTO;
import com.easy.cloud.core.generator.code.java.rule.EcGenerateJavaClassRule;
import com.easy.cloud.core.generator.code.xml.pojo.bo.mybatis.EcGenerateXmlMybatisBO;
import com.easy.cloud.core.generator.code.xml.pojo.dto.EcGenerateXmlMybatisDTO;

/** 生成java代码测试类 */
public class GenerateJavaTest {
	// 数据库配置信息
	private EcDatabaseAbstactConfig databaseAbstactConfig;
	// pojo类所在的项目名称
	private String projectNamePojo = "easy-cloud-core-jdbc";
	// dao类所在的项目名称
	private String projectNameDao = "easy-cloud-core";
	// service类所在的项目名称
	private String projectNameService = "easy-cloud-core";
	// controller类所在的项目名称
	private String projectNameController = "easy-cloud-core";
	// mybatis配置文件所在的项目名称
	private String projectNameMybatis = "easy-cloud-core";
	// 基础包名称
	private String basePackageName = "com.easy.cloud.core.jdbc";

	// 表名
	private String tableName = "easy_user_info";
	// 模块包名
	private String moduleName = "user";
	// 类主体名称
	private String classBodyName = "User";
	// 类的注释
	private String classComment = "用户";
	Map<String, Boolean> ignoreFields = new HashMap<>();

	@Before
	public void initData() {
		// 数据库配置信息
		databaseAbstactConfig = new EcDataBaseMysqlConfig();
		// 设置数据库基础url
		databaseAbstactConfig.buildDatabaseBaseUrl("jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com");
		// 设置数据库端口号
		databaseAbstactConfig.buildDatabasePort("3306");
		// 设置数据库名称
		databaseAbstactConfig.buildDatabaseName("dq_easy_cloud");
		// 设置数据库用户名
		databaseAbstactConfig.buildDatabaseUserName("dq_easy_cloud");
		// 设置数据库密码
		databaseAbstactConfig.buildDatabasePassword("dq_easy_cloud123");
		// 设置数据库表名
		databaseAbstactConfig.buildTableName(tableName);
		
		ignoreFields.put(EcIgnoreField.AVAILABLE, true);
		ignoreFields.put(EcIgnoreField.CREATE_BY, true);
		ignoreFields.put(EcIgnoreField.CREATE_DATE, true);
		ignoreFields.put(EcIgnoreField.ID, true);
		ignoreFields.put(EcIgnoreField.IS_DELETED, true);
		ignoreFields.put(EcIgnoreField.REMARK, true);
		ignoreFields.put(EcIgnoreField.UPDATE_BY, true);
		ignoreFields.put(EcIgnoreField.UPDATE_DATE, true);
		ignoreFields.put(EcIgnoreField.VERSION, true);
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
		// generateJavaDAOByDataBase();
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
		String subModulePackageName = "entity";
		EcGenerateJavaBaseDTO generateJavaBaseDTO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName,
				moduleName, subModulePackageName, classBodyName, classComment);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, true);
		generateJavaBaseDTO.setCoverSwith(true);
		generateJavaBaseDTO.setIgnoreFields(ignoreFields);
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
		generateJavaBaseDTO.setIgnoreFields(ignoreFields);
		EcGenerateRule generateRule = new EcGenerateJavaClassRule(true, true, true, false);
		try {
			EcGenerateJavaVOBO generateJavaVOBO = new EcGenerateJavaVOBO(generateJavaBaseDTO, generateRule);
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
	public void generateMybatis() {
		// 获取dao子模块包名
		String daoSubModulePackageName = EcSubModuleDefaultPackageName.DAO_INF;
		// 创建EcGenerateJavaBaseDTO对象
		EcGenerateJavaBaseDTO daoDto = new EcGenerateJavaBaseDTO(projectNameDao, basePackageName, moduleName,
				daoSubModulePackageName, classBodyName, classComment);
		// 获取DAO层完整的包名
		String fullPackageNameDAO = daoDto.buildFullPackageName();
		// 获取DAO层简称类类型
		String simpleClassTypeDAO = classBodyName + EcClassNameEndWith.DAO_INF;
		// 获取DAO层完整类类型
		String fullClsssTypeDAO = EcCodeGenerateUtils.getFullClassType(fullPackageNameDAO, simpleClassTypeDAO);
		// 创建EcGenerateXmlMybatisDTO实例对象
		EcGenerateXmlMybatisDTO mybatisDTO = new EcGenerateXmlMybatisDTO();
		// 设置命名空间
		mybatisDTO.setNamespace(fullClsssTypeDAO);
		// 设置标明
		mybatisDTO.setTableName(tableName);
		// 设置mybatis配置文件所在的项目名称
		mybatisDTO.setProjectName(projectNameMybatis);
		// 设置子路径名称
		mybatisDTO.setSubPath("mybatis");

		// 持久化对象的子模块包路径
		String subModulePackageNameDO = EcSubModuleDefaultPackageName.POJO_PO;
		// 创建EcGenerateJavaBaseDTO，持久化对象的数据传输对象
		EcGenerateJavaBaseDTO dtoDO = new EcGenerateJavaBaseDTO(projectNamePojo, basePackageName, moduleName,
				subModulePackageNameDO, classBodyName, classComment);
		// 获取持久化对象的完整包名
		String fullPackageNameDO = dtoDO.buildFullPackageName();
		// 获取持久化对象的简称类类型
		String simpleClassTypeDO = classBodyName + EcClassNameEndWith.POJO_PO;
		// 获取持久化对象的完整类类型
		String fullClsssTypeDO = EcCodeGenerateUtils.getFullClassType(fullPackageNameDO, simpleClassTypeDO);

		// 设置持久化对象的简称类类型
		mybatisDTO.setSimpleClassTypeDO(simpleClassTypeDO);
		// 设置持久化对象的完整类类型
		mybatisDTO.setFullClassTypeDO(fullClsssTypeDO);
		// 设置保存mapper配置文件集合的配置文件名称（自动将mapping追加到该文件的mappers节点中）
		mybatisDTO.setMappersConfigName("sqlmap-config");

		// 创建业务逻辑对象
		EcGenerateXmlMybatisBO mybatisBO = new EcGenerateXmlMybatisBO(mybatisDTO);
		// 数据初始化
		mybatisBO.initData();
		// 构建数据库数据源对象
		mybatisBO.buildDatabaseDataSources(new EcMysqlDataSources(databaseAbstactConfig));
		// 构建根节点
		mybatisBO.buildRootContentElementDesc();
		// 构建ResultMap节点
		mybatisBO.buildResultMap();
		// 构建ColumnList节点
		mybatisBO.buildColumnList();
		// 构建根据id获取对象信息的节点
		mybatisBO.buildFindById();
		// 构建获取列表计数的节点
		mybatisBO.buildListCount();
		// 构建列表分页信息的节点
		mybatisBO.buildListPage();
		// 构建设置列名的sql节点
		mybatisBO.buildSetColumnSql();
		// 构建保存对象信息的节点
		mybatisBO.buildSave();
		// 构建更新数据的节点
		mybatisBO.buildUpdate();
		try {
			// 调用生成代码方法
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
