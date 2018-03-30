package com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo.mybatis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.file.pojo.bo.DqFileBO;
import com.dq.easy.cloud.module.common.generator.code.base.pojo.desc.DqTemplateDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqDocType;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqIgnoreSetField;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisAttrKey;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisElementNameEnum;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisSqlTypeEnum;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqTableColumnKey;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlContentElementDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.desc.DqXmlMybatisContentElementDesc;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.bo.DqGenerateXmlBaseBO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlBaseDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisDTO;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisData;
import com.dq.easy.cloud.module.common.generator.code.xml.utils.DqGenerateCodeXmlUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

/**
 * 
 * <p>
 * 生成mybatis的xml文件
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 上午11:27:36
 */
public class DqGenerateXmlMybatisBO extends DqGenerateXmlBaseBO {
	private DqGenerateXmlMybatisDTO mybatisDTO;
	private DqXmlContentElementDesc rootElement;

	public DqGenerateXmlMybatisBO(DqTemplateDesc templateDesc, DqGenerateXmlMybatisDTO generateXmlBaseDTO) {
		super(null, templateDesc);
		mybatisDTO = generateXmlBaseDTO;
	}

	@Override
	public void generateCode() throws Exception {
		// 生成的文件
		File generateFile = new DqFileBO(super.getFileDesc()).newFile();
		// 文件存在执行更新原xml数据
		if (generateFile.exists()) {
			updateMybatisXmlData(generateFile);
		} else {
			super.generateCode();
			
		}
		
	}

	/**
	 * 更新mybatis的xml数据
	 * 
	 * @throws IOException
	 * @throws JDOMException
	 */
	protected void updateMybatisXmlData(File generateFile) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(generateFile);
		Element rootElement = doc.getRootElement(); // 获取根元素
//		操作更新的数据
		doUpdateMybatisXmlData(rootElement);
		Format format = Format.getPrettyFormat();
        format.setIndent("    ");
        format.setEncoding("UTF-8");
        XMLOutputter out = new XMLOutputter(format);
        out.output(doc, new FileWriter(generateFile)); //写文件
	}
	/** 操作更新的数据---子类可以重写此方法进行更新定制化 */
	protected void doUpdateMybatisXmlData (Element rootElement) {
		// 更新当前表对应的resultMap节点的子节点		
		updateReultMapChidren(rootElement);
		// 更新当前表对应的id为ColumnList的Text
		updateColumnListText(rootElement);
		// 更新当前表对应的id为setColumnSql的子节点
		updateSetColumnSqlChidren(rootElement);
	}
	
	/** 更新id为setColumnSql的子节点 */
	private void updateSetColumnSqlChidren(Element rootElement) {
		Element setColumnSqlElement = findElementById(DqMyBatisSqlTypeEnum.SET_COLUMN_SQL.getDesc(), rootElement, DqMyBatisElementNameEnum.SELECT.getDesc());
		Element setElement = setColumnSqlElement.getChild(DqMyBatisElementNameEnum.SET.getDesc());
		if (DqBaseUtils.isNull(setElement)) {
			setElement = new Element(DqMyBatisElementNameEnum.SET.getDesc());
			setColumnSqlElement.addContent(setElement);
		}
		List<Element> ifElements = new ArrayList<>();
		for (DqGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			Element ifElement = new Element(DqMyBatisElementNameEnum.IF.getDesc());
			ifElement.setAttribute(DqMyBatisAttrKey.TEST, getTestAttrValue(data));
			ifElement.setText(DqGenerateCodeXmlUtils.getWhereDataStr(data, mybatisDTO.getTableSimpleName()));
			ifElements.add(ifElement);
		}
		setElement.removeContent();
		setElement.setContent(ifElements);
	}
	/** 更新id为columnList的Text */
	private void updateColumnListText(Element rootElement) {
		Element columnListElement = findElementById(DqMyBatisSqlTypeEnum.COLUMN_LIST.getDesc(), rootElement, DqMyBatisElementNameEnum.SQL.getDesc());
		columnListElement.setText(mybatisDTO.buildDatasStr());
	}
	/** 更新resultMap的子节点 */
	private void updateReultMapChidren(Element rootElement) {
		Element currentDOResultMapElement = findElementById(mybatisDTO.getSimpleClassTypeDO(), rootElement, DqMyBatisElementNameEnum.RESULT_MAP.getDesc());
		List<Element> currentResultMapElementChirden = currentDOResultMapElement.getChildren();
		// 保存需要不需要替换的其他节点
		List<Element> otherElements = new ArrayList<>();
		for (Element element : currentResultMapElementChirden) {
			boolean equelsResult = DqStringUtils.equals(element.getName(), DqMyBatisElementNameEnum.RESULT.getDesc());
			boolean equelsId = DqStringUtils.equals(element.getName(), DqMyBatisElementNameEnum.ID.getDesc());
			if (equelsResult || equelsId){
				continue;
			}
			otherElements.add(element);
		}
		// 获取resultElements和idElement
		Element idElement = new Element(DqMyBatisElementNameEnum.ID.getDesc());
		List<Element> resultElements = new ArrayList<>();
		for (DqGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			String elementName = null;
			if (DqTableColumnKey.isPrimary(data.getColumnKey())) {
				elementName = DqMyBatisElementNameEnum.ID.getDesc();
			} else {
				elementName = DqMyBatisElementNameEnum.RESULT.getDesc();
			}
			Element element = new Element(elementName); 
			element.setAttribute(DqMyBatisAttrKey.COLUMN, data.getColunmName());
			element.setAttribute(DqMyBatisAttrKey.JDBC_TYPE, data.getColunmType());
			element.setAttribute(DqMyBatisAttrKey.PROPERTY, data.getPropertyName());
			// 不是主键添加到result列表中			
			if (DqTableColumnKey.isPrimary(data.getColumnKey())) {
				idElement = element;
				continue;
			 }
			resultElements.add(element);
		}
		// 构建新的ResultMapChildren节点列表	
		List<Element> newResultMapChildren = new ArrayList<>();
		newResultMapChildren.add(idElement);
		newResultMapChildren.addAll(resultElements);
		newResultMapChildren.addAll(otherElements);
		
		currentDOResultMapElement.removeContent();
		currentDOResultMapElement.addContent(newResultMapChildren);
	}
	/** 根据findIdValue从parentElement的子元素名称的列表中获取指定元素 */
	private Element findElementById(String findIdValue, Element parentElement, String elementName) {
		List<Element> resultMapElements = parentElement.getChildren(elementName);
		Element currentDOResultMapElement = null;
		if (DqCollectionsUtils.isEmpty(resultMapElements)) {
			throw new RuntimeException(elementName + "元素不存在");
		}
		// 获取id为当前表所对应的resultMap
		for (Element resultMapElement : resultMapElements) {
			// 获取当前元素的指定属性
			String idValue = resultMapElement.getAttributeValue(DqMyBatisAttrKey.ID);
			if (DqStringUtils.equals(idValue, findIdValue)) {
				currentDOResultMapElement = resultMapElement;
				break;
			}
		}
		if (DqBaseUtils.isNull(currentDOResultMapElement)) {
			throw new RuntimeException("当前表对应的"+ elementName +"元素不存在");
		}
		return currentDOResultMapElement;
	}
	public DqGenerateXmlMybatisBO buildRootContentElementDesc() {
		rootElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		rootElement.setElementName(DqMyBatisElementNameEnum.MAPPER.getDesc());
		rootElement.addAttribute(DqMyBatisAttrKey.NAMESPACE, mybatisDTO.getNamespace());
		rootElement.buildDataByDatabaseSources(databaseDataSources);
		super.xmlContentDocDesc.setRootElement(rootElement);
		return this;
	}

	@Override
	protected String getDocType() {
		return DqDocType.MYBATIS;
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
		resultMapElement.setSqlType(DqMyBatisSqlTypeEnum.RESULT_MAP.getType());

		resultMapElement.addAttribute(DqMyBatisAttrKey.ID, mybatisDTO.getSimpleClassTypeDO());
		resultMapElement.addAttribute(DqMyBatisAttrKey.TYPE, mybatisDTO.getFullClassTypeDO());

		List<DqXmlContentElementDesc> resultMapChildes = new ArrayList<>();
		for (DqGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			DqXmlContentElementDesc elementDesc = new DqXmlContentElementDesc();
			if (DqTableColumnKey.isPrimary(data.getColumnKey())) {
				elementDesc.setElementName(DqMyBatisElementNameEnum.ID.getDesc());
			} else {
				elementDesc.setElementName(DqMyBatisElementNameEnum.RESULT.getDesc());
			}
			elementDesc.addAttribute(DqMyBatisAttrKey.COLUMN, data.getColunmName());
			elementDesc.addAttribute(DqMyBatisAttrKey.JDBC_TYPE, data.getColunmType());
			elementDesc.addAttribute(DqMyBatisAttrKey.PROPERTY, data.getPropertyName());

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
		columnListElement.setSqlType(DqMyBatisSqlTypeEnum.COLUMN_LIST.getType());
		columnListElement.addAttribute(DqMyBatisAttrKey.ID, getColumnList());
		columnListElement.setText(mybatisDTO.buildDatasStr());
		rootElement.addElement(columnListElement);
		return this;
	}

	/** 构建根据id查询结果 */
	public DqGenerateXmlMybatisBO buildFindById() {
		DqXmlMybatisContentElementDesc findByIdElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		findByIdElement.setElementName(DqMyBatisElementNameEnum.SELECT.getDesc());
		findByIdElement.setSqlType(DqMyBatisSqlTypeEnum.FIND_BY_ID.getType());

		findByIdElement.addAttribute(DqMyBatisAttrKey.ID, getFindById());
		findByIdElement.addAttribute(DqMyBatisAttrKey.RESULT_MAP, mybatisDTO.getSimpleClassTypeDO());
		if (mybatisDTO.getPrimaryKey() != null) {
			findByIdElement.addAttribute(DqMyBatisAttrKey.PARAMETER_TYPE,
					mybatisDTO.getPrimaryKey().getPropertyFullType());
		}
		// 构建include子节点
		DqXmlMybatisContentElementDesc includeElement = new DqXmlMybatisContentElementDesc();
		includeElement.setElementName(DqMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(DqMyBatisAttrKey.REFID, getColumnList());
		findByIdElement.addElement(includeElement);

		findByIdElement.addWhereData(mybatisDTO.getPrimaryKey());
		rootElement.addElement(findByIdElement);
		return this;
	}

	/** 构建ListCount */
	public DqGenerateXmlMybatisBO buildListCount() {
		DqXmlMybatisContentElementDesc listCountElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		listCountElement.setElementName(DqMyBatisElementNameEnum.SELECT.getDesc());
		listCountElement.setSqlType(DqMyBatisSqlTypeEnum.LIST_COUNT.getType());

		listCountElement.addAttribute(DqMyBatisAttrKey.ID, getListCount());
		listCountElement.addAttribute(DqMyBatisAttrKey.RESULT_TYPE, Integer.class.getName());
		listCountElement.addAttribute(DqMyBatisAttrKey.PARAMETER_TYPE, "map");

		rootElement.addElement(listCountElement);
		return this;
	}

	/** 构建ListPage */
	public DqGenerateXmlMybatisBO buildListPage() {
		DqXmlMybatisContentElementDesc listPageElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		listPageElement.setElementName(DqMyBatisElementNameEnum.SELECT.getDesc());
		listPageElement.setSqlType(DqMyBatisSqlTypeEnum.LIST_PAGE.getType());
		// 构建listPage元素的属性列表
		listPageElement.addAttribute(DqMyBatisAttrKey.ID, getListPage());
		listPageElement.addAttribute(DqMyBatisAttrKey.RESULT_MAP, mybatisDTO.getSimpleClassTypeDO());
		listPageElement.addAttribute(DqMyBatisAttrKey.PARAMETER_TYPE, "map");
		// 构建include子节点
		DqXmlMybatisContentElementDesc includeElement = new DqXmlMybatisContentElementDesc();
		includeElement.setElementName(DqMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(DqMyBatisAttrKey.REFID, getColumnList());
		listPageElement.addElement(includeElement);

		rootElement.addElement(listPageElement);
		return this;
	}

	/** 构建setColumnSql */
	public DqGenerateXmlMybatisBO buildSetColumnSql() {
		DqXmlMybatisContentElementDesc setColumnSqlElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		setColumnSqlElement.setElementName(DqMyBatisElementNameEnum.SELECT.getDesc());
		setColumnSqlElement.setSqlType(DqMyBatisSqlTypeEnum.SET_COLUMN_SQL.getType());
		// 设置属性
		setColumnSqlElement.addAttribute(DqMyBatisAttrKey.ID, getSetColumnSql());
		// 构建set子节点
		DqXmlMybatisContentElementDesc setElement = new DqXmlMybatisContentElementDesc();
		setElement.setElementName(DqMyBatisElementNameEnum.SET.getDesc());
		for (DqGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			if (DqIgnoreSetField.isIgnoreField(data.getPropertyName())) {
				continue;
			}
			DqXmlMybatisContentElementDesc ifElement = new DqXmlMybatisContentElementDesc();
			ifElement.setElementName(DqMyBatisElementNameEnum.IF.getDesc());
			// 设置if节点test为key的值
			ifElement.addAttribute(DqMyBatisAttrKey.TEST, getTestAttrValue(data));
			// 设置text
			ifElement.setText(getIfElementText(data));
			setElement.addElement(ifElement);
		}
		setColumnSqlElement.addElement(setElement);
		rootElement.addElement(setColumnSqlElement);
		return this;
	}

	/** 获取test属性的值 */
	private String getTestAttrValue(DqGenerateXmlMybatisData data) {
		// 设置属性
		StringBuilder testAttrValue = DqStringUtils.newStringBuilderDefault();
		testAttrValue.append(data.getPropertyName());
		testAttrValue.append(DqSymbol.EMPTY).append("!=").append(DqSymbol.EMPTY).append("null");
		return testAttrValue.toString();
	}

	/** 获取if元素的Text值 */
	private String getIfElementText(DqGenerateXmlMybatisData data) {
		return DqGenerateCodeXmlUtils.getMybatisColunmMappingPropertyByData(data, mybatisDTO.getTableSimpleName())
				+ DqSymbol.COMMA;
	}

	/** 构建save */
	public DqGenerateXmlMybatisBO buildSave() {
		DqXmlMybatisContentElementDesc saveElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		saveElement.setElementName(DqMyBatisElementNameEnum.INSERT.getDesc());
		saveElement.setSqlType(DqMyBatisSqlTypeEnum.SAVE.getType());

		saveElement.addAttribute(DqMyBatisAttrKey.ID, getSave());
		saveElement.addAttribute(DqMyBatisAttrKey.USE_GENERATED_KEYS, "true");
		saveElement.addAttribute(DqMyBatisAttrKey.PARAMETER_TYPE, mybatisDTO.getFullClassTypeDO());
		// 构建include子节点
		DqXmlMybatisContentElementDesc includeElement = new DqXmlMybatisContentElementDesc();
		includeElement.setElementName(DqMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(DqMyBatisAttrKey.REFID, getSetColumnSql());
		saveElement.addElement(includeElement);

		rootElement.addElement(saveElement);
		return this;
	}

	/** 构建update */
	public DqGenerateXmlMybatisBO buildUpdate() {
		DqXmlMybatisContentElementDesc updateElement = new DqXmlMybatisContentElementDesc(mybatisDTO);
		updateElement.setElementName(DqMyBatisElementNameEnum.UPDATE.getDesc());
		updateElement.setSqlType(DqMyBatisSqlTypeEnum.UPDATE.getType());

		updateElement.addAttribute(DqMyBatisAttrKey.ID, getUpdate());
		updateElement.addAttribute(DqMyBatisAttrKey.PARAMETER_TYPE, mybatisDTO.getFullClassTypeDO());
		// 构建include子节点
		DqXmlMybatisContentElementDesc includeElement = new DqXmlMybatisContentElementDesc();
		includeElement.setElementName(DqMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(DqMyBatisAttrKey.REFID, getSetColumnSql());

		updateElement.addElement(includeElement);
		updateElement.addWhereData(mybatisDTO.getPrimaryKey());
		rootElement.addElement(updateElement);
		return this;
	}

	public String getColumnList() {
		return DqMyBatisSqlTypeEnum.COLUMN_LIST.getDesc();
	}

	public String getFindById() {
		return DqMyBatisSqlTypeEnum.FIND_BY_ID.getDesc();
	}

	public String getListCount() {
		return DqMyBatisSqlTypeEnum.LIST_COUNT.getDesc();
	}

	public String getListPage() {
		return DqMyBatisSqlTypeEnum.LIST_PAGE.getDesc();
	}

	public String getSetColumnSql() {
		return DqMyBatisSqlTypeEnum.SET_COLUMN_SQL.getDesc();
	}

	public String getSave() {
		return DqMyBatisSqlTypeEnum.SAVE.getDesc();
	}

	public String getUpdate() {
		return DqMyBatisSqlTypeEnum.UPDATE.getDesc();
	}
}
