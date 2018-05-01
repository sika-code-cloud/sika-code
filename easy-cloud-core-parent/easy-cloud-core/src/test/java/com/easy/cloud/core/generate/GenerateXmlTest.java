package com.easy.cloud.core.generate;

import org.junit.Before;
import org.junit.Test;

import com.easy.cloud.core.basic.pojo.bo.EcBaseBO;
import com.easy.cloud.core.common.generator.code.base.config.database.EcDatabaseAbstactConfig;
import com.easy.cloud.core.common.generator.code.base.config.database.mysql.EcDataBaseMysqlConfig;
import com.easy.cloud.core.common.generator.code.base.sources.database.mysql.EcMysqlDataSources;
import com.easy.cloud.core.common.generator.code.xml.pojo.bo.mybatis.EcGenerateXmlMybatisBO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisDTO;

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
	private String projectNameMybatis = "dq-easy-cloud-common";

	// 表名
	private String tableName = "p_user";

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
		mybatisDTO.setNamespace("com.lxzl.payment.gateway.dataaccess.dao.mysql.customer.BusinessCustomerMapper");
		mybatisDTO.setTableName(tableName);
		mybatisDTO.setProjectName(projectNameMybatis);
		mybatisDTO.setSubPath("mybatis");
		mybatisDTO.setSimpleClassTypeDO(EcBaseBO.class.getSimpleName());
		mybatisDTO.setFullClassTypeDO(EcBaseBO.class.getName());
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
