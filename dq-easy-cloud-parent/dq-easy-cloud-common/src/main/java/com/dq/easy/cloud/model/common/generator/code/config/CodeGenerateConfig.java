package com.dq.easy.cloud.model.common.generator.code.config;

import java.util.Date;

import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;

/**
 * 
 * <p>
 * 代码生成工具所需的配置信息
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月21日 下午4:48:32
 */
public class CodeGenerateConfig {
	/** 默认获取计算机的用户名 */
	public static final String AUTHOR_DEFAULT = System.getenv().get("USERNAME");
	/** 创建时间 */
	public static final String CREATE_DATE = DqDateFormatUtils.format(new Date(), DqDateFormatUtils.FORMAT_NORMAL);
	public static final String DATABASE_URL = "jdbc:mysql://rm-wz9632z95v9v65458o.mysql.rds.aliyuncs.com:3306/sea_share_db";
	public static final String DATABASE_USERNAME = "seashare";
	public static final String DATABASE_PASSWORD = "Seashare123";
	public static final String DRIVER = "com.mysql.jdbc.Driver";

}
