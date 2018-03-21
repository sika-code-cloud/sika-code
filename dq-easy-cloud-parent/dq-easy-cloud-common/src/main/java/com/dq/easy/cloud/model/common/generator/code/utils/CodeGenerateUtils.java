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

import com.dq.easy.cloud.model.common.generator.code.constant.CodeGenerateConstant.DqIgnoreField;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.ColumnClassDTO;

import freemarker.template.Template;

/**
 * 描述：代码生成器 Created by Ay on 2017/5/1.
 */
public class CodeGenerateUtils {

	private final String AUTHOR = System.getenv().get("USERNAME");
	private final String CURRENT_DATE = "2017/05/03";
	private final String tableName = "tl_cyg_bkbgbase_info";
	private final String packageName = "com.dq.easy.cloud.user";
	private final String tableAnnotation = "质量问题";
	private final String URL = "jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com:3306/sea_share_db";
	private final String USER = "seashare";
	private final String PASSWORD = "Seashare123";
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String diskPath = System.getProperty("user.dir")+"\\src\\main\\java\\" ;
	private final String changeTableName = replaceUnderLineAndUpperCase(tableName);

	public Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

	public static void main(String[] args) throws Exception {
		CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
		codeGenerateUtils.generate();
	}

	public void generate() throws Exception {
		try {
			Connection connection = getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
			// 生成Mapper文件
//			generateMapperFile(resultSet);
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
			generateDOFile(resultSet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

		}
	}

	private void generateDOFile(ResultSet resultSet) throws Exception {

		final String suffix = ".java";
		String modelPath = createMkDir("pojo/entity");
		final String path = diskPath + modelPath + changeTableName + suffix;
		final String templateName = "POJO_DO.ftl";
		File mapperFile = new File(path);
		List<ColumnClassDTO> columnClassList = new ArrayList<>();
		ColumnClassDTO columnClass = null;
		while (resultSet.next()) {
			// id字段略过
			if (DqIgnoreField.isIgnoreField(resultSet.getString("COLUMN_NAME"))){
				continue;
			}
			columnClass = new ColumnClassDTO();
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
		String [] packageNameArray = packageName.split("\\.");
		String modelPath = "";
		for (int i = 0 ; i < packageNameArray.length ; ++i) {
			modelPath = modelPath + packageNameArray[i] +"\\";
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
		Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
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

	public String replaceUnderLineAndUpperCase(String str) {
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

}
