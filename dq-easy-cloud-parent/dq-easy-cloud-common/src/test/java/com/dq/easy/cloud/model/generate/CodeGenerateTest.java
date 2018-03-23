package com.dq.easy.cloud.model.generate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dq.easy.cloud.module.basic.constant.DqBaseConstant.DqFileSuffix;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;
import com.dq.easy.cloud.module.common.generator.code.config.database.DqDatabaseAbstactConfig;
import com.dq.easy.cloud.module.common.generator.code.config.database.mysql.DqDataBaseMysqlConfig;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqClassNameEndWith;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqSourceCodePathRelative;
import com.dq.easy.cloud.module.common.generator.code.constant.DqCodeGenerateConstant.DqTemplateName;
import com.dq.easy.cloud.module.common.generator.code.pojo.bo.DqCodeGenerateBaseBO;
import com.dq.easy.cloud.module.common.generator.code.pojo.bo.common.DqCodeGenerateCommonBO;
import com.dq.easy.cloud.module.common.generator.code.pojo.bo.database.DqCodeGenerateDatabaseBO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.DqFieldBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.common.DqCodeGenerateCommonDTO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.common.DqFieldCommonDTO;
import com.dq.easy.cloud.module.common.generator.code.pojo.dto.database.DqCodeGenerateDatabaseDTO;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;

/**
 * 
 * <p>
 * 代码生成组件测试类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月23日 上午10:48:01
 */
public class CodeGenerateTest {
	private DqDatabaseAbstactConfig databaseAbstactConfig;
	private String packageStartWith = "com.dq.easy.cloud";
	private String needSubstrClassNameStartWith = "payment";
	@Before
	public void initData() {
		databaseAbstactConfig = new DqDataBaseMysqlConfig();
		databaseAbstactConfig.buildDatabaseBaseUrl("jdbc:mysql://192.168.10.205");
		databaseAbstactConfig.buildDatabasePort("3306");
		databaseAbstactConfig.buildDatabaseName("lxzl_payment_gateway");
		databaseAbstactConfig.buildDatabasePassword("lxzldev");
		databaseAbstactConfig.buildDatabaseUserName("lxzldev");
	}
//	测试生成模块
	@Test
	public void testGenerateModule() {
		testGenerateDefaultModule("payorder", "payment_pay_order", "支付单表");
	}
	public void testGenerateDefaultModule(String moduleName, String tableName , String fileComment) {
		generateDO(moduleName, tableName, fileComment);
		generateDTO(moduleName, tableName, fileComment);
		testGenerateBO();
	}
	@Test
	public void testGenerateoDO() {
		generateDO("payorder", "payment_pay_order", "支付单表");
	}
	@Test
	public void testGenerateDTO() {
		generateDTO("payorder", "payment_pay_order", "支付单表");
	}
	@Test
	public void testGenerateBO() {
		generateBO("payorder", "PayOrder", "支付单表");
	}
	@Test
	public void testGenerateQuery() {
		
	}
	@Test
	public void testGenerateDAOInf() {
		
	}
	@Test
	public void testGenerateDAOImpl() {
		
	}
	@Test
	public void testGenerateServiceInf() {
		
	}
	@Test
	public void testGenerateServiceImpl() {
		
	}
	@Test
	public void testGenerateLogicInf() {
		
	}
	@Test
	public void testGenerateLogicImpl() {
		
	}
	@Test
	public void testGenerateController() {
		
	}
	
	/**
	 * 
	 * <p>
	 * 生成DO
	 * </p>
	 *
	 * @param moduleName : String : 模块名称
	 * @param tableName : String : 表名
	 * @param fileComment : String : 文件注释
	 * @author daiqi
	 * 创建时间    2018年3月23日 下午4:06:13
	 */
	public void generateDO(String moduleName, String tableName , String fileComment) {
		DqCodeGenerateDatabaseDTO dqCodeGenerateDatabaseDTO = getDqCodeGenerateDatabase(moduleName, tableName, fileComment);
		
		dqCodeGenerateDatabaseDTO.setSubModuleRelativePackageName("pojo.entity");
		dqCodeGenerateDatabaseDTO.setClassNameEndWith(DqClassNameEndWith.POJO_DO);
		dqCodeGenerateDatabaseDTO.setTemplateName(DqTemplateName.POJO_DO);
		DqCodeGenerateBaseBO dqCodeGenerateBaseBO = new DqCodeGenerateDatabaseBO(dqCodeGenerateDatabaseDTO);
		dqCodeGenerateBaseBO.initCodeGenerateData();
		
		dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().setExtendsParentClass(DqBaseEntity.class.getSimpleName());
		
		Set<String> importClazzs = dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getImportClazzs();
		importClazzs.add(DqBaseEntity.class.getName());
		importClazzs.add(Entity.class.getName());
		importClazzs.add(Table.class.getName());
		importClazzs.add(Column.class.getName());
		dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().setImportClazzs(importClazzs);
		doGenerateFileByTemplate(dqCodeGenerateBaseBO);
	}
	/**
	 * 
	 * <p>
	 * 生成DTO
	 * </p>
	 *
	 * @param moduleName : String : 模块名称
	 * @param tableName : String : 表名
	 * @param fileComment : String : 文件注释
	 * @author daiqi
	 * 创建时间    2018年3月23日 下午4:06:13
	 */
	public void generateDTO(String moduleName, String tableName , String fileComment) {
		DqCodeGenerateDatabaseDTO dqCodeGenerateDatabaseDTO = getDqCodeGenerateDatabase(moduleName, tableName, fileComment);
		
		dqCodeGenerateDatabaseDTO.setSubModuleRelativePackageName("pojo.dto");
		dqCodeGenerateDatabaseDTO.setClassNameEndWith(DqClassNameEndWith.POJO_DTO);
		dqCodeGenerateDatabaseDTO.setTemplateName(DqTemplateName.POJO_DTO);
		
		DqCodeGenerateBaseBO dqCodeGenerateBaseBO = new DqCodeGenerateDatabaseBO(dqCodeGenerateDatabaseDTO);
		dqCodeGenerateBaseBO.initCodeGenerateData();
//		设置继承的父类
		String classNameBody = dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getClassNameBody();
		dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().setExtendsParentClass(classNameBody+DqClassNameEndWith.POJO_DO);
//		设置需要导入的class
		Set<String> importClazzs = new HashSet<>();
		String doPackageName = dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getModelBasePackageName() + ".pojo.entity" + "." + dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getExtendsParentClass();
		importClazzs.add(doPackageName);
//		关闭根据属性导入class
		dqCodeGenerateBaseBO.setCloseImportFieldClazzs(true);
		dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().setImportClazzs(importClazzs);
		doGenerateFileByTemplate(dqCodeGenerateBaseBO);
	}
	/**
	 * 
	 * <p>
	 * 生成BO
	 * </p>
	 *
	 * @param moduleName : String : 模块名称
	 * @param classNameBody : String : 类名主体
	 * @param fileComment : String : 文件注释
	 * @author daiqi
	 * 创建时间    2018年3月23日 下午4:06:13
	 */
	public void generateBO(String moduleName, String classNameBody , String fileComment) {
		DqCodeGenerateCommonDTO dqCodeGenerateCommonDTO = getDqCodeGenerateCommonDTO(moduleName, classNameBody, fileComment); 
		dqCodeGenerateCommonDTO.setSubModuleRelativePackageName("pojo.bo");
		dqCodeGenerateCommonDTO.setClassNameEndWith(DqClassNameEndWith.POJO_BO);
		dqCodeGenerateCommonDTO.setTemplateName(DqTemplateName.POJO_BO);
		
		List<DqFieldBaseDTO> dqFieldBaseDTOs = new ArrayList<>();
		DqFieldBaseDTO dqFieldBaseDTO = new DqFieldCommonDTO();
		dqFieldBaseDTO.setFieldComment("数据传输对象");
		dqFieldBaseDTO.setFieldName(classNameBody + DqClassNameEndWith.POJO_DTO);
		dqFieldBaseDTO.setFieldType(classNameBody + DqClassNameEndWith.POJO_DTO);
		dqFieldBaseDTOs.add(dqFieldBaseDTO);
		dqCodeGenerateCommonDTO.setFieldDTOs(dqFieldBaseDTOs);
		
		DqCodeGenerateBaseBO dqCodeGenerateBaseBO = new DqCodeGenerateCommonBO(dqCodeGenerateCommonDTO);
		dqCodeGenerateBaseBO.initCodeGenerateData();
		
		
		dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().setExtendsParentClass(DqBaseBO.class.getSimpleName());
		
		Set<String> importClazzs = dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getImportClazzs();
		importClazzs.add(DqBaseBO.class.getName());
		importClazzs.add(Autowired.class.getName());
		String dtoPackageName = dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getModelBasePackageName() + ".pojo.dto" + "." + dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().getClassNameBody() + DqClassNameEndWith.POJO_DTO;
		importClazzs.add(dtoPackageName);
		dqCodeGenerateBaseBO.getDqCodeGenerateBaseDTO().setImportClazzs(importClazzs);
		doGenerateFileByTemplate(dqCodeGenerateBaseBO);
	}
	public DqCodeGenerateDatabaseDTO getDqCodeGenerateDatabase(String moduleName, String tableName , String fileComment) {
		DqCodeGenerateDatabaseDTO dqCodeGenerateDatabaseDTO = new DqCodeGenerateDatabaseDTO(databaseAbstactConfig);
		dqCodeGenerateDatabaseDTO.setModelBasePackageName(packageStartWith + DqSymbol.STOP + moduleName);
		dqCodeGenerateDatabaseDTO.setTableNameLower(tableName);
		dqCodeGenerateDatabaseDTO.setFileComment(fileComment);
		
		dqCodeGenerateDatabaseDTO.setNeedSubstrClassNameStartWith(needSubstrClassNameStartWith);
		dqCodeGenerateDatabaseDTO.setFileSuffix(DqFileSuffix.JAVA);
		dqCodeGenerateDatabaseDTO.setSourceFilePathRelative(DqSourceCodePathRelative.JAVA);
		return dqCodeGenerateDatabaseDTO;
	}
	public DqCodeGenerateCommonDTO getDqCodeGenerateCommonDTO(String moduleName, String classNameBody , String fileComment) {
		DqCodeGenerateCommonDTO dqCodeGenerateCommonDTO = new DqCodeGenerateCommonDTO();
		dqCodeGenerateCommonDTO.setModelBasePackageName(packageStartWith + DqSymbol.STOP + moduleName);
		dqCodeGenerateCommonDTO.setFileComment(fileComment);
		dqCodeGenerateCommonDTO.setClassNameBody(classNameBody);
		
		dqCodeGenerateCommonDTO.setNeedSubstrClassNameStartWith(needSubstrClassNameStartWith);
		dqCodeGenerateCommonDTO.setFileSuffix(DqFileSuffix.JAVA);
		dqCodeGenerateCommonDTO.setSourceFilePathRelative(DqSourceCodePathRelative.JAVA);
		return dqCodeGenerateCommonDTO;
	}
	
	
	public void doGenerateFileByTemplate(DqCodeGenerateBaseBO dqCodeGenerateBaseBO) {
		
		dqCodeGenerateBaseBO.verifyCodeGenerateData();
		try {
			dqCodeGenerateBaseBO.generateFileByTemplate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
