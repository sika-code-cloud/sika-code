package com.easy.cloud.core.common.generator.code.xml.pojo.dto;

import java.util.List;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.constant.EcFileConstant.EcFileSuffix;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcTableColumnKey;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * mybatis的xml数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 下午12:47:38
 */
public class EcGenerateXmlMybatisDTO extends EcGenerateXmlBaseDTO {
	/** 命名空间---即dao类完整类型 */
	private String namespace;
	/** 表名 */
	private String tableName;
	/** 表明简写 */
	private String tableSimpleName;
	/** 完整的类类型---持久化类 */
	private String fullClassTypeDO;
	/** 简称的类类型---持久化类 */
	private String simpleClassTypeDO;
	/** mybatis的映射列表配置名称 */
	private String mappersConfigName;
	/** 主键 */
	private EcGenerateXmlMybatisData primaryKey;
	/** 数据列表 */
	private List<EcGenerateXmlMybatisData> datas;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableSimpleName() {
		if (EcStringUtils.isEmpty(tableSimpleName) && EcStringUtils.isNotEmpty(tableName)) {
			tableSimpleName = EcStringUtils.getRmUnderLineSimpleStr(tableName);
		}
		return tableSimpleName;
	}

	public void setTableSimpleName(String tableSimpleName) {
		this.tableSimpleName = tableSimpleName;
	}

	public String getFullClassTypeDO() {
		return fullClassTypeDO;
	}

	public void setFullClassTypeDO(String fullClassTypeDO) {
		this.fullClassTypeDO = fullClassTypeDO;
	}

	public String getSimpleClassTypeDO() {
		return simpleClassTypeDO;
	}

	public void setSimpleClassTypeDO(String simpleClassTypeDO) {
		this.simpleClassTypeDO = simpleClassTypeDO;
	}

	public List<EcGenerateXmlMybatisData> getDatas() {
		return datas;
	}

	public void setDatas(List<EcGenerateXmlMybatisData> datas) {
		this.datas = datas;
	}

	public String getMappersConfigName() {
		return mappersConfigName;
	}
	
	public String getFullMappersConfigName() {
		StringBuilder sb = EcStringUtils.newStringBuilderDefault();
		sb.append(getMappersConfigName());
		sb.append(EcSymbol.STOP).append(EcFileSuffix.XML);
		return sb.toString();
	}
	public void setMappersConfigName(String mappersConfigName) {
		this.mappersConfigName = mappersConfigName;
	}
	/** 获取表xml的相对路径 */
	public String getTableXmlRelativePath() {
		StringBuilder sb = EcStringUtils.newStringBuilderDefault();
		sb.append(getSubPath()).append(EcSymbol.FORWARD_SLASH);
		sb.append(getTableName()).append(EcSymbol.STOP).append(EcFileSuffix.XML);
		return sb.toString();
	}

	public EcGenerateXmlMybatisData getPrimaryKey() {
		if (primaryKey != null) {
			return primaryKey;
		}
		for (EcGenerateXmlMybatisData data : datas) {
			if (EcTableColumnKey.isPrimary(data.getColumnKey())) {
				primaryKey = data;
			}
		}
		return primaryKey;
	}

	public void setPrimaryKey(EcGenerateXmlMybatisData primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String buildDatasStr() {
		if (EcCollectionsUtils.isEmpty(datas)) {
			return null;
		}
		StringBuilder sb = EcStringUtils.newStringBuilderDefault();
		for (int i = 0; i < datas.size(); ++i) {
			EcGenerateXmlMybatisData data = datas.get(i);
			sb.append(getTableSimpleName());
			sb.append(EcSymbol.STOP).append(data.getColunmName());
			if (i < datas.size() - 1) {
				sb.append(EcSymbol.COMMA);
			}
		}
		return sb.toString();
	}
}
