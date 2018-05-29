package com.easy.cloud.core.generator.code;

import org.junit.Before;
import org.junit.Test;

import com.easy.cloud.core.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.generator.code.base.config.database.mysql.EcDataBaseMysqlConfig;
import com.easy.cloud.core.generator.code.base.sources.database.mysql.EcMysqlDataSources;
import com.easy.cloud.core.generator.code.xml.pojo.bo.mybatis.EcGenerateXmlMybatisBO;
import com.easy.cloud.core.generator.code.xml.pojo.dto.EcGenerateXmlMybatisDTO;

/**
 * 
 * <p>
 * 测试生成xml
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 下午2:38:46
 */
public class GenerateXmlTest {
	// 数据库配置信息
	private EcDatabaseAbstactConfig databaseAbstactConfig;
	// pojo类所在的项目名称
	private String projectNameMybatis = "easy-cloud-core-jdbc";

	// 表名
	private String tableName = "easy_user_info";

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

	@Test
	public void generateMybatis() throws Exception {
		EcGenerateXmlMybatisDTO mybatisDTO = new EcGenerateXmlMybatisDTO();
		mybatisDTO.setCoverSwith(true);
//		mybatisDTO.setNamespace(UserDAO.class.getName());
//		mybatisDTO.setTableName(tableName);
//		mybatisDTO.setProjectName(projectNameMybatis);
//		mybatisDTO.setSubPath("mapper");
//		mybatisDTO.setSimpleClassTypeDO(UserEntity.class.getSimpleName());
//		mybatisDTO.setFullClassTypeDO(UserEntity.class.getName());
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
		mybatisBO.generateCode();
	}
}
