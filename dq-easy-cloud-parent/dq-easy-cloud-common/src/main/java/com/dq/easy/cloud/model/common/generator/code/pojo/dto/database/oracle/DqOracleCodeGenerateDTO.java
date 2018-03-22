package com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.oracle;

import com.dq.easy.cloud.model.common.generator.code.config.DqCodeGenerateConfig;
import com.dq.easy.cloud.model.common.generator.code.pojo.dto.database.DqCodeGenerateDatabaseAbstractDTO;

/**
 * 
 * <p>
 * oracle代码生成数据传输对象
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
 * 创建时间    2018年3月22日 下午3:13:00
 */
public class DqOracleCodeGenerateDTO extends DqCodeGenerateDatabaseAbstractDTO{

	@Override
	public String getDatabaseDriver() {
		return DqCodeGenerateConfig.DATABASE_ORACLE_DRIVER;
	}

}
