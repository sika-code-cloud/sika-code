package com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.mysql;

import com.dq.easy.cloud.model.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.DqCodeGenerateDatabaseAbstractDTO;

/**
 * 
 * <p>
 * mysql代码生成数据传输对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年3月22日 下午3:11:36
 */
public class DqMysqlCodeGenerateDTO extends DqCodeGenerateDatabaseAbstractDTO{

	@Override
	public String getDatabaseDriver() {
		return DqCodeGenerateConfig.DATABASE_MYSQL_DRIVER;
	}
	
}
