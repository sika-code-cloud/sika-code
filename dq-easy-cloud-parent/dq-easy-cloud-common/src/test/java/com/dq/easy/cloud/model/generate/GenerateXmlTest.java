package com.dq.easy.cloud.model.generate;

import org.junit.Before;
import org.junit.Test;

import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.common.generator.code.base.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.base.config.database.mysql.DqDataBaseMysqlConfig;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.mysql.DqMysqlDataSources;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo.mybatis.DqGenerateXmlMybatisBO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisDTO;

/**
 * 
 * <p>
 * 测试生成xml
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月29日 下午2:38:46
 */
public class GenerateXmlTest {
	// 数据库配置信息
		private DqDatabaseAbstactConfig databaseAbstactConfig;
		// 模版描述对象
		private DqTemplateDesc templateDesc = new DqTemplateDesc(DqCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH,
				"MYBATIS.ftl");;
		// pojo类所在的项目名称
		private String projectNameMybatis = "dq-easy-cloud-common";

		// 表名
		private String tableName = "p_user";
		// 模块包名
		private String moduleName = "user";
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
	
	@Test
	public void generateMybatis() throws Exception {
		DqGenerateXmlMybatisDTO mybatisDTO = new DqGenerateXmlMybatisDTO();
		mybatisDTO.setCoverSwith(true);
		mybatisDTO.setNamespace("com.lxzl.payment.gateway.dataaccess.dao.mysql.customer.BusinessCustomerMapper");
		mybatisDTO.setTableName(tableName);
		mybatisDTO.setTableSimpleName("pu");
		mybatisDTO.setProjectName(projectNameMybatis);
		mybatisDTO.setSubPath("mybatis");
		mybatisDTO.setSimpleClassTypeDO(DqBaseBO.class.getSimpleName());
		mybatisDTO.setFullClassTypeDO(DqBaseBO.class.getName());
		
		DqGenerateXmlMybatisBO mybatisBO = new DqGenerateXmlMybatisBO(templateDesc,mybatisDTO);
		mybatisBO.initData();
		
		mybatisBO.buildDatabaseDataSources(new DqMysqlDataSources(databaseAbstactConfig));
		mybatisBO.buildRootContentElementDesc();
		mybatisBO.buildResultMap();
		mybatisBO.buildColumnList();
		mybatisBO.buildFindById();
		mybatisBO.generateCode();
	}
}
