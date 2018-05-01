package com.easy.cloud.core.common.generator.code.xml.desc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.pojo.desc.EcFileContentBaseDesc;
import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcColumnLabel;
import com.easy.cloud.core.common.generator.code.base.pojo.rule.EcGenerateRule;
import com.easy.cloud.core.common.generator.code.base.sources.database.EcDatabaseDataSources;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcTableColumnKey;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlBaseDTO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisDTO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisData;
import com.easy.cloud.core.common.generator.code.xml.utils.EcGenerateCodeXmlUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * mybatis内容元素描述类
 * </p>
 * 
 * @author daiqi
 * 创建时间    2018年3月30日 下午2:24:25
 */

public class EcXmlMybatisContentElementDesc extends EcXmlContentElementDesc {
	/** 数据传输对象 */
	private EcGenerateXmlMybatisDTO mybatisDTO;
	/** sql的类型对应@see DqCodeGenerateXmlConstant.DqMyBatisSqlType */
	private Integer sqlType;
	/** mybatis的where数据列表 */
	private List<EcGenerateXmlMybatisData> whereDatas;

	public EcXmlMybatisContentElementDesc() {
		super();
	}

	public EcXmlMybatisContentElementDesc(EcGenerateRule generateRule) {
		super(generateRule);
	}

	public EcXmlMybatisContentElementDesc(EcGenerateXmlBaseDTO generateXmlBaseDTO) {
		super(generateXmlBaseDTO);
		this.mybatisDTO = (EcGenerateXmlMybatisDTO) generateXmlBaseDTO;
	}

	public Integer getSqlType() {
		return sqlType;
	}

	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}

	public List<EcGenerateXmlMybatisData> getWhereDatas() {
		return whereDatas;
	}

	public void setWhereDatas(List<EcGenerateXmlMybatisData> whereDatas) {
		this.whereDatas = whereDatas;
	}

	public EcGenerateXmlMybatisDTO getMybatisDTO() {
		return mybatisDTO;
	}

	public EcFileContentBaseDesc addWhereData(EcGenerateXmlMybatisData whereData) {
		if (EcCollectionsUtils.isEmpty(this.whereDatas)) {
			this.whereDatas = new ArrayList<>();
		}
		this.whereDatas.add(whereData);
		return this;
	}

	public EcFileContentBaseDesc addAllWhereData(List<EcGenerateXmlMybatisData> whereDatas) {
		if (EcCollectionsUtils.isEmpty(this.whereDatas)) {
			this.whereDatas = new ArrayList<>();
		}
		this.whereDatas.addAll(whereDatas);
		return this;
	}

	@Override
	public EcFileContentBaseDesc buildDataByDatabaseSources(EcDatabaseDataSources databaseDataSources) {
		List<EcGenerateXmlMybatisData> mybatisDatas = new ArrayList<>();
		ResultSet resultSet = databaseDataSources.getResultSet();
		if (EcBaseUtils.isNull(resultSet)) {
			return this;
		}
		try {
			ResultSet resultSetPrimaryKey = databaseDataSources.getPrimaryKeyResultSet();
			String primaryKeyName = null;
			while (resultSetPrimaryKey.next()) {
				primaryKeyName = resultSetPrimaryKey.getString("COLUMN_NAME");
			}
			while (resultSet.next()) {
				String name = EcStringUtils
						.replaceUnderLineAndUpperCase(resultSet.getString(EcColumnLabel.COLUMN_NAME));
				EcGenerateXmlMybatisData generateXmlMybatisData = new EcGenerateXmlMybatisData();
				// 字段在数据库的注释
				generateXmlMybatisData.setColunmName(resultSet.getString(EcColumnLabel.COLUMN_NAME));
				generateXmlMybatisData.setPropertyName(name);
				generateXmlMybatisData.setColunmType(resultSet.getString(EcColumnLabel.TYPE_NAME));
				if (EcStringUtils.equalsIgnoreCase(generateXmlMybatisData.getColunmName(), primaryKeyName)) {
					generateXmlMybatisData.setColumnKey(EcTableColumnKey.PRI);
				}
				mybatisDatas.add(generateXmlMybatisData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		mybatisDTO.setDatas(mybatisDatas);
		return this;
	}
	
	/** 获取where数据列表字符串格式 */
	public String getWhereDatasStr() {
		if (EcBaseUtils.isNotNull(mybatisDTO)) {
			return EcGenerateCodeXmlUtils.getWhereDatasStr(whereDatas, mybatisDTO.getTableSimpleName());
		}
		return null;
	}
	
}
