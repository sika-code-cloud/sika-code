package com.dq.easy.cloud.model.common.generator.code.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.dq.easy.cloud.model.common.generator.code.constant.DqCodeGenerateConstant.DqCodeProject;
import com.dq.easy.cloud.model.common.generator.code.constant.DqCodeGenerateConstant.DqIgnoreField;
import com.dq.easy.cloud.model.common.generator.code.pojo.bo.DqCodeGenerateBaseBO;
import com.dq.easy.cloud.model.common.generator.code.pojo.bo.database.DqCodeGenerateDatabaseBO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.DqCodeGenerateBaseDTO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.DqColumnClassDTO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.DqFieldDTO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.DqCodeGenerateDatabaseAbstractDTO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.DqFieldDatabaseDTO;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.mysql.DqMysqlCodeGenerateDTO;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

import freemarker.template.Template;

/**
 * 描述：代码生成器 Created by Ay on 2017/5/1.
 */
public class DqCodeGenerateUtils {

	private final String AUTHOR = System.getenv().get("USERNAME");
	private final String CURRENT_DATE = "2017/05/03";
	private final String tableName = "tl_cyg_bkbgbase_info";
	private final String packageName = "com.dq.easy.cloud.user";
	private final String tableAnnotation = "质量问题";
	private final String URL = "jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com:3306/sea_share_db";
	private final String USER = "seashare";
	private final String PASSWORD = "Seashare123";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String diskPath = System.getProperty("user.dir") + "\\src\\main\\java\\";
	private final String changeTableName = replaceUnderLineAndUpperCase(tableName);

	public Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

	public static void main(String[] args) throws Exception {
		DqCodeGenerateUtils dqCodeGenerateUtils = new DqCodeGenerateUtils();
		System.out.println(dqCodeGenerateUtils.replaceUnderLineAndUpperCase(dqCodeGenerateUtils.tableName));

		 dqCodeGenerateUtils.generate();
	}

	public void generate() throws Exception {
		try {
			Connection connection = getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
			// 生成Mapper文件
			// generateMapperFile(resultSet);
			// 生成Dao文件
			generateDaoFile(resultSet);
			// 生成服务层接口文件
			generateServiceInterfaceFile(resultSet);
			// 生成服务实现层文件
			generateServiceImplFile(resultSet);
			// 生成Controller层文件
			generateControllerFile(resultSet);
			// 生成DTO文件
			generateDTOFile(resultSet);
			// 生成Model文件
//			generateDOFile(resultSet);
			generateDOFileUseDTO(resultSet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

		}
	}
	private void generateDOFileUseDTO(ResultSet resultSet) throws Exception {
		DqCodeGenerateDatabaseAbstractDTO dqCodeGenerateDatabaseDTO = new DqMysqlCodeGenerateDTO();
		dqCodeGenerateDatabaseDTO.setFileSuffix(DqCodeProject.SOURCE_CODE_SUFFIX);
		dqCodeGenerateDatabaseDTO.setModelBasePackageName(packageName);
		dqCodeGenerateDatabaseDTO.setSubModuleRelativePackageName("pojo.entity");
		dqCodeGenerateDatabaseDTO.setTableNameLower(tableName);
		dqCodeGenerateDatabaseDTO.setClassNameBody(changeTableName);
		dqCodeGenerateDatabaseDTO.setClassNameEndWith("Entity");
		dqCodeGenerateDatabaseDTO.setTemplateName("POJO_DO.ftl");
		List<DqFieldDTO> dqFieldDTOs = new ArrayList<>();
		while (resultSet.next()) {
			// id字段略过
			if (DqIgnoreField.isIgnoreField(resultSet.getString("COLUMN_NAME"))) {
				continue;
			}
			DqFieldDatabaseDTO dqFieldDTO = new DqFieldDatabaseDTO();
			// 获取字段名称
			dqFieldDTO.setTableColumnName(resultSet.getString("COLUMN_NAME"));
			// 获取字段类型
			dqFieldDTO.setTableColumnType(resultSet.getString("TYPE_NAME"));
			// 字段在数据库的注释
			dqFieldDTO.setFieldComment(resultSet.getString("REMARKS"));
			dqFieldDTO.setFieldName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
			dqFieldDTOs.add(dqFieldDTO);
		}
		dqCodeGenerateDatabaseDTO.setFieldDTOs(dqFieldDTOs);
		DqCodeGenerateBaseBO dqCodeGenerateBaseBO = new DqCodeGenerateDatabaseBO(dqCodeGenerateDatabaseDTO);
		dqCodeGenerateBaseBO.initCodeGenerateData();
		dqCodeGenerateBaseBO.verifyCodeGenerateData();
		dqCodeGenerateBaseBO.generateFileByTemplate();

	}
	private void generateDOFile(ResultSet resultSet) throws Exception {

		final String suffix = ".java";
		String modelPath = createMkDir("pojo/entity");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "POJO_DO.ftl";
		File mapperFile = new File(path);
		List<DqColumnClassDTO> columnClassList = new ArrayList<>();
		DqColumnClassDTO columnClass = null;
		while (resultSet.next()) {
			// id字段略过
			if (DqIgnoreField.isIgnoreField(resultSet.getString("COLUMN_NAME"))) {
				continue;
			}
			columnClass = new DqColumnClassDTO();
			// 获取字段名称
			columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
			// 获取字段类型
			columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
			// 转换字段名称，如 sys_name 变成 SysName
			columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
			// 字段在数据库的注释
			columnClass.setColumnComment(resultSet.getString("REMARKS"));
			columnClassList.add(columnClass);
		}
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_column", columnClassList);
		generateFileByTemplate(templateName, mapperFile, dataMap);

	}

	private String createMkDir(String sunName) {
		String[] packageNameArray = packageName.split("\\.");
		String modelPath = "";
		for (int i = 0; i < packageNameArray.length; ++i) {
			modelPath = modelPath + packageNameArray[i] + "\\";
		}
		modelPath = modelPath + sunName + "\\";
		String mkdirPath = diskPath + modelPath;
		File mkdirFile = new File(mkdirPath);
		if (!mkdirFile.exists()) {
			mkdirFile.mkdirs();
		}
		return modelPath;
	}

	private void generateDTOFile(ResultSet resultSet) throws Exception {
		final String suffix = "DTO.java";
		String modelPath = createMkDir("pojo/dto");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "POJO_DTO.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateControllerFile(ResultSet resultSet) throws Exception {
		final String suffix = "Controller.java";
		String modelPath = createMkDir("controller");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "Controller.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateServiceImplFile(ResultSet resultSet) throws Exception {
		final String suffix = "ServiceImpl.java";
		String modelPath = createMkDir("service//impl");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "ServiceImpl.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception {
		final String suffix = "Service.java";
		String modelPath = createMkDir("service");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "ServiceInf.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);
	}

	private void generateDaoFile(ResultSet resultSet) throws Exception {
		final String suffix = "DAO.java";
		String modelPath = createMkDir("repository");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "DAOInf.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);

	}

	private void generateMapperFile(ResultSet resultSet) throws Exception {
		final String suffix = "Mapper.xml";
		final String path = diskPath + changeTableName + suffix;
		final String templateName = "Mapper.ftl";
		File mapperFile = new File(path);
		Map<String, Object> dataMap = new HashMap<>();
		generateFileByTemplate(templateName, mapperFile, dataMap);

	}

	private void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap)
			throws Exception {
		Template template = DqFreeMarkerTemplateUtils.getTemplate(templateName);
		FileOutputStream fos = new FileOutputStream(file);
		dataMap.put("table_name_small", tableName);
		dataMap.put("table_name", changeTableName);
		dataMap.put("author", AUTHOR);
		dataMap.put("date", CURRENT_DATE);
		dataMap.put("package_name", packageName);
		dataMap.put("table_annotation", tableAnnotation);
		Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
		template.process(dataMap, out);
	}

	public static String replaceUnderLineAndUpperCase(String str) {
		int infoIndex = str.indexOf("_info");
		if (infoIndex > 0) {
			str = str.substring(0, infoIndex);
		}
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		String result = sb.toString().replaceAll("_", "");
		return StringUtils.capitalize(result);
	}

	/**
	 * 
	 * <p>
	 * 根据包名获取完整包路径
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     codeGenerateBaseDTO.modelBasePackageName : : 模块包名 : 是
	 *     codeGenerateBaseDTO.subModulePelativeBaseName : : 子模块包名 : 是
	 * </pre>
	 *
	 * @param codeGenerateBaseDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午7:25:53
	 */
	public static String getPackagePathFull(DqCodeGenerateBaseDTO codeGenerateBaseDTO) {
		String modelBasePackageName = codeGenerateBaseDTO.getModelBasePackageName();
		String subModulePelativeBaseName = codeGenerateBaseDTO.getSubModuleRelativePackageName();
		
		StringBuilder packageFullPathBuild = DqStringUtils.newStringBuilderDefault();
		packageFullPathBuild.append(codeGenerateBaseDTO.getProjectRootPath());
		packageFullPathBuild.append(changePackageNameToPath(modelBasePackageName));
		packageFullPathBuild.append(changePackageNameToPath(subModulePelativeBaseName));
		return packageFullPathBuild.toString();
	}
	
	/**
	 * 
	 * <p>
	 * 根据codeGenerateBaseDTO获取完整文件路径
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param codeGenerateBaseDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午7:28:16
	 */
	public static String getFilePathFull(DqCodeGenerateBaseDTO codeGenerateBaseDTO) {
		StringBuilder fileFullPathBuild = DqStringUtils.newStringBuilderDefault();
		if (DqStringUtils.isEmpty(codeGenerateBaseDTO.getPackgePathFull())) {
			fileFullPathBuild.append(getPackagePathFull(codeGenerateBaseDTO));
		} else {
			fileFullPathBuild.append(codeGenerateBaseDTO.getPackgePathFull());
		}
		fileFullPathBuild.append(codeGenerateBaseDTO.getClassNameStartWith());
		fileFullPathBuild.append(codeGenerateBaseDTO.getClassNameEndWith());
		
		return fileFullPathBuild.toString();
	}
	
	/**
	 * 
	 * <p>
	 * 将包名转换为路径字符串
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param packegeName
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午7:32:59
	 */
	public static String changePackageNameToPath(String packegeName) {
		String [] packageNameArr = packegeName.split("\\.");
		StringBuilder packagePathBuild = DqStringUtils.newStringBuilderDefault();
		for (String tempModelBasePackageName : packageNameArr) {
			packagePathBuild.append(tempModelBasePackageName).append(DqSymbol.BACK_SLASH);
		}
		return packagePathBuild.toString();
	}
	
	/**
	 * 
	 * <p>
	 * 获取完整的类名
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param codeGenerateBaseDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午8:15:24
	 */
	public static String getClassNameFull(DqCodeGenerateBaseDTO codeGenerateBaseDTO) {
		StringBuilder classNameFull = DqStringUtils.newStringBuilderDefault();
		if (DqStringUtils.isNotEmpty(codeGenerateBaseDTO.getClassNameStartWith())) {
			classNameFull.append(codeGenerateBaseDTO.getClassNameStartWith());
		}
		if (DqStringUtils.isNotEmpty(codeGenerateBaseDTO.getClassNameBody())) {
			classNameFull.append(codeGenerateBaseDTO.getClassNameBody());
		}
		if (DqStringUtils.isNotEmpty(codeGenerateBaseDTO.getClassNameEndWith())) {
			classNameFull.append(codeGenerateBaseDTO.getClassNameEndWith());
		}
		return classNameFull.toString();
	}
	
	/**
	 * 
	 * <p>
	 * 获取完整的包名
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param codeGenerateBaseDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月22日 下午8:35:15
	 */
	public static String getPackageNameFull(DqCodeGenerateBaseDTO codeGenerateBaseDTO) {
		StringBuilder packageNameFullBuild = DqStringUtils.newStringBuilderDefault();
		packageNameFullBuild.append(codeGenerateBaseDTO.getModelBasePackageName());
		
		if (DqStringUtils.isNotEmpty(codeGenerateBaseDTO.getSubModuleRelativePackageName())) {
			packageNameFullBuild.append(DqSymbol.STOP).append(codeGenerateBaseDTO.getSubModuleRelativePackageName());
		}
		return packageNameFullBuild.toString();
	}
}
