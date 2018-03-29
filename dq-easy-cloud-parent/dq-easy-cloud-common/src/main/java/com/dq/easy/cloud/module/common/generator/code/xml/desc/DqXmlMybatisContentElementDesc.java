package com.dq.easy.cloud.module.common.generator.code.xml.desc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.file.pojo.desc.DqFileContentBaseDesc;
import com.dq.easy.cloud.module.common.generator.code.base.constant.DqCodeGenerateConstant.DqColumnLabel;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.rule.DqGenerateRule;
import com.dq.easy.cloud.module.common.generator.code.base.sources.database.DqDatabaseDataSources;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisSqlType;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqTableColumnKey;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisData;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

public class DqXmlMybatisContentElementDesc extends DqXmlContentElementDesc {
	private DqGenerateXmlMybatisDTO mybatisDTO;
	private Integer sqlType;
	private List<DqGenerateXmlMybatisData> whereDatas;

	public DqXmlMybatisContentElementDesc() {
		super();
	}

	public DqXmlMybatisContentElementDesc(DqGenerateRule generateRule) {
		super(generateRule);
	}

	public DqXmlMybatisContentElementDesc(DqGenerateXmlBaseDTO generateXmlBaseDTO) {
		super(generateXmlBaseDTO);
		this.mybatisDTO = (DqGenerateXmlMybatisDTO) generateXmlBaseDTO;
	}

	public Integer getSqlType() {
		return sqlType;
	}

	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}

	public List<DqGenerateXmlMybatisData> getWhereDatas() {
		return whereDatas;
	}

	public void setWhereDatas(List<DqGenerateXmlMybatisData> whereDatas) {
		this.whereDatas = whereDatas;
	}

	public DqFileContentBaseDesc addWhereData(DqGenerateXmlMybatisData whereData) {
		if (DqCollectionsUtils.isEmpty(this.whereDatas)) {
			whereDatas = new ArrayList<>();
		}
		whereDatas.add(whereData);
		return this;
	}

	public DqFileContentBaseDesc addAllWhereData(List<DqGenerateXmlMybatisData> whereDatas) {
		if (DqCollectionsUtils.isEmpty(this.whereDatas)) {
			whereDatas = new ArrayList<>();
		}
		whereDatas.addAll(whereDatas);
		return this;
	}

	@Override
	public DqFileContentBaseDesc buildDataByDatabaseSources(DqDatabaseDataSources databaseDataSources) {
		List<DqGenerateXmlMybatisData> mybatisDatas = new ArrayList<>();
		ResultSet resultSet = databaseDataSources.getResultSet();
		if (DqBaseUtils.isNull(resultSet)) {
			return this;
		}
		try {
			ResultSet resultSetPrimaryKey = databaseDataSources.getPrimaryKeyResultSet();
			String primaryKeyName = null;
			while (resultSetPrimaryKey.next()) {
				primaryKeyName = resultSetPrimaryKey.getString("COLUMN_NAME");
			}
			while (resultSet.next()) {
				String name = DqStringUtils
						.replaceUnderLineAndUpperCase(resultSet.getString(DqColumnLabel.COLUMN_NAME));
				DqGenerateXmlMybatisData generateXmlMybatisData = new DqGenerateXmlMybatisData();
				// 字段在数据库的注释
				generateXmlMybatisData.setColunmName(resultSet.getString(DqColumnLabel.COLUMN_NAME));
				generateXmlMybatisData.setPropertyName(name);
				generateXmlMybatisData.setColunmType(resultSet.getString(DqColumnLabel.TYPE_NAME));
				if (DqStringUtils.equalsIgnoreCase(generateXmlMybatisData.getColunmName(),primaryKeyName)) {
					generateXmlMybatisData.setColumnKey(DqTableColumnKey.PRI);
				}
				mybatisDatas.add(generateXmlMybatisData);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mybatisDTO.setDatas(mybatisDatas);
		return this;
	}

	public boolean isColumnList() {
		if (this.sqlType != null && sqlType == DqMyBatisSqlType.COLUMN_LIST.getType()) {
			return true;
		}
		return false;
	}
}
