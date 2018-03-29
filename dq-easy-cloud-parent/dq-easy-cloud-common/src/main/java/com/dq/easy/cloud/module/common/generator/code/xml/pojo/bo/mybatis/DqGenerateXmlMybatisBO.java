package com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.base.utils.DqCodeGenerateUtils;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqDocType;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisAttrKey;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisElementNameEnum;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisSqlType;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqTableColumnKey;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlContentElementAttributeDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlContentElementDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlMybatisContentElementDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo.DqGenerateXmlBaseBO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisData;

/**
 * 
 * <p>
 * 生成mybatis的xml文件
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年3月29日 上午11:27:36
 */
public class DqGenerateXmlMybatisBO extends DqGenerateXmlBaseBO{
	private DqGenerateXmlMybatisDTO mybatisDTO;
	private DqXmlContentElementDesc rootElement;
	public DqGenerateXmlMybatisBO(DqTemplateDesc templateDesc, DqGenerateXmlMybatisDTO generateXmlBaseDTO) {
		super(null, templateDesc);
		mybatisDTO = generateXmlBaseDTO;
	}

	@Override
	protected String getDocType() {
		return DqDocType.MYBATIS;
	}

	public DqGenerateXmlMybatisBO buildRootContentElementDesc() {
		rootElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		rootElement.setElementName(DqMyBatisElementNameEnum.MAPPER.getDesc());
		List<DqXmlContentElementAttributeDesc> elementAttributeDescs = new ArrayList<>();
		elementAttributeDescs.add(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.NAMESPACE, mybatisDTO.getNamespace()));
		rootElement.setAttributes(elementAttributeDescs);
		rootElement.buildDataByDatabaseSources(databaseDataSources);
		super.xmlContentDocDesc.setRootElement(rootElement);
		return this;
	}

	@Override
	protected DqGenerateXmlBaseDTO getGenerateXmlBaseDTO() {
		return mybatisDTO;
	}

	@Override
	protected String getFileName() {
		return mybatisDTO.getTableName();
	}

	@Override
	protected String getSourceCodeRelativePath() {
		return mybatisDTO.getSourceCodeRelativePath();
	}

	/** 构建resultMap */
	public DqGenerateXmlMybatisBO buildResultMap() {
		DqXmlMybatisContentElementDesc resultMapElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		resultMapElement.setElementName(DqMyBatisElementNameEnum.RESULT_MAP.getDesc());
		List<DqXmlContentElementAttributeDesc> attributes = new ArrayList<>();
		attributes.add(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.ID, mybatisDTO.getSimpleClassTypeDO()));
		attributes.add(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.TYPE, mybatisDTO.getFullClassTypeDO()));
		resultMapElement.setAttributes(attributes);
		
		List<DqXmlContentElementDesc> resultMapChildes = new ArrayList<>();
		for (DqGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			DqXmlContentElementDesc elementDesc = new DqXmlContentElementDesc();
			if (DqTableColumnKey.isPrimary(data.getColumnKey())) {
				elementDesc.setElementName(DqMyBatisElementNameEnum.ID.getDesc());
			} else {
				elementDesc.setElementName(DqMyBatisElementNameEnum.RESULT.getDesc());
			}
			List<DqXmlContentElementAttributeDesc> tempAttributes = new ArrayList<>();
			tempAttributes.add(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.COLUNM, data.getColunmName()));
			tempAttributes.add(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.JDBC_TYPE, data.getColunmType()));
			tempAttributes.add(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.PROPERTY, data.getPropertyName()));
			elementDesc.setAttributes(tempAttributes);
			
			resultMapChildes.add(elementDesc);
		}
		resultMapElement.addAllElement(resultMapChildes);
		
		rootElement.addElement(resultMapElement);
		return this;
	}
	
	/** 构建列的列表 */
	public DqGenerateXmlMybatisBO buildColumnList() {
		DqXmlMybatisContentElementDesc columnListElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		columnListElement.setElementName(DqMyBatisElementNameEnum.SQL.getDesc());
		columnListElement.addAttribute(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.ID, getColumnList()));
		columnListElement.setText(mybatisDTO.buildDatasStr());
		columnListElement.setSqlType(DqMyBatisSqlType.COLUMN_LIST.getType());
		rootElement.addElement(columnListElement);
		return this;
	}
	
	/** 构建根据id查询结果 */
	public DqGenerateXmlMybatisBO buildFindById() {
		DqXmlMybatisContentElementDesc findByIdElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		findByIdElement.setElementName(DqMyBatisElementNameEnum.SELECT.getDesc());
		findByIdElement.addAttribute(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.ID, getColumnList()));
		findByIdElement.addAttribute(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.RESULT_MAP, mybatisDTO.getSimpleClassTypeDO()));
		if (mybatisDTO.getPrimaryKey() != null) {
			findByIdElement.addAttribute(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.PARAMETER_TYPE, DqCodeGenerateUtils.getJavaFullClassTypeOfMysql(mybatisDTO.getPrimaryKey().getColunmType())));
		}
		findByIdElement.setSqlType(DqMyBatisSqlType.FIND_BY_ID.getType());
		
		DqXmlMybatisContentElementDesc includeElement = new DqXmlMybatisContentElementDesc();
		includeElement.setElementName(DqMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(new DqXmlContentElementAttributeDesc(DqMyBatisAttrKey.REFID, getColumnList()));
		
		findByIdElement.addWhereData(mybatisDTO.getPrimaryKey());
		rootElement.addElement(findByIdElement);
		return this;
	}
	
	public String getColumnList() {
		return DqMyBatisSqlType.COLUMN_LIST.getDesc();
	}
}
