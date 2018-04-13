package com.easy.cloud.core.common.generator.code.xml.pojo.bo.mybatis;

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

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.file.pojo.bo.EcFileBO;
import com.easy.cloud.core.common.file.utils.EcFileUtils;
import com.easy.cloud.core.common.generator.code.base.config.EcCodeGenerateConfig;
import com.easy.cloud.core.common.generator.code.base.constant.EcCodeGenerateConstant.EcTemplateName;
import com.easy.cloud.core.common.generator.code.base.pojo.desc.EcTemplateDesc;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcDocType;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcIgnoreSetField;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcMyBatisAttrKey;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcMyBatisElementNameEnum;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcMyBatisSqlTypeEnum;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcTableColumnKey;
import com.easy.cloud.core.common.generator.code.xml.desc.EcXmlContentElementDesc;
import com.easy.cloud.core.common.generator.code.xml.desc.EcXmlMybatisContentElementDesc;
import com.easy.cloud.core.common.generator.code.xml.pojo.bo.EcGenerateXmlBaseBO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlBaseDTO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisDTO;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisData;
import com.easy.cloud.core.common.generator.code.xml.utils.EcGenerateCodeXmlUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 生成mybatis的xml文件
 * </p>
 *
 * @author daiqi 创建时间 2018年3月29日 上午11:27:36
 */
public class EcGenerateXmlMybatisBO extends EcGenerateXmlBaseBO {
	private EcGenerateXmlMybatisDTO mybatisDTO;
	private EcXmlContentElementDesc rootElement;

	public EcGenerateXmlMybatisBO(EcTemplateDesc templateDesc, EcGenerateXmlMybatisDTO generateXmlBaseDTO) {
		super(null, templateDesc);
		mybatisDTO = generateXmlBaseDTO;
	}

	public EcGenerateXmlMybatisBO(EcGenerateXmlMybatisDTO generateXmlBaseDTO) {
		this(null, generateXmlBaseDTO);
		super.setTemplateDesc(
				new EcTemplateDesc(EcCodeGenerateConfig.CODE_TEMPLATE_BASE_PACKAGE_PATH, EcTemplateName.MYBATIS_XML));
	}

	@Override
	public void generateCode() throws Exception {
		// 生成的文件
		File generateFile = new EcFileBO(super.getFileDesc()).newFile();
		// 文件存在执行更新原xml数据
		if (generateFile.exists()) {
			updateMybatisXmlData(generateFile);
		} else {
			super.generateCode();

		}
		updateMappingConfig();
	}

	/**
	 * 
	 * <p>
	 * 更新映射配置文件
	 * </p>
	 *
	 * @param generateFile
	 * @author daiqi 创建时间 2018年3月31日 上午9:36:12
	 * @throws Exception
	 */
	private void updateMappingConfig() throws Exception {
		// 获取完整的文件名称
		String mappingFileName = mybatisDTO.getFullMappersConfigName();
		// 获取项目完整的路径
		String projectFullPath = EcFileUtils.getTargetProjectPath(null, mybatisDTO.getProjectName(),
				EcCodeGenerateConfig.getNeedFilterDirectoryName());
		// 获取mappingConfig文件
		File mappingConfigFile = EcFileUtils.getFileByFileName(projectFullPath, mappingFileName);
		if (EcBaseUtils.isNull(mappingConfigFile)) {
			throw new RuntimeException("映射集合文件未找到");
		}
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(mappingConfigFile);
		Element rootElement = doc.getRootElement(); // 获取根元素
		// 更新mappers元素
		updateMappersElement(rootElement);
		// 执行xml输出
		doXmlOutputter(doc, mappingConfigFile);
	}

	/** 更新MappersElement元素 */
	private void updateMappersElement(Element rootElement) {
		Element mappsElement = rootElement.getChild(EcMyBatisElementNameEnum.MAPPERS.getDesc());
		List<Element> mapperElements = mappsElement.getChildren();
		String tableXmlRelativePath = mybatisDTO.getTableXmlRelativePath();
		for (Element elment : mapperElements) {
			if (EcStringUtils.equals(elment.getAttributeValue(EcMyBatisAttrKey.RESOURCE), tableXmlRelativePath)) {
				return;
			}
		}
		Element mapperElement = new Element(EcMyBatisAttrKey.MAPPER);
		mapperElement.setAttribute(EcMyBatisAttrKey.RESOURCE, tableXmlRelativePath);
		mappsElement.addContent(mapperElement);

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
		// 操作更新的数据
		doUpdateMybatisXmlData(rootElement);
		doXmlOutputter(doc, generateFile);
	}

	private void doXmlOutputter(Document doc, File xmlFile) throws IOException {
		Format format = Format.getPrettyFormat();
		format.setIndent("    ");
		format.setEncoding("UTF-8");
		XMLOutputter out = new XMLOutputter(format);
		out.output(doc, new FileWriter(xmlFile)); // 写文件
	}

	/** 操作更新的数据---子类可以重写此方法进行更新定制化 */
	protected void doUpdateMybatisXmlData(Element rootElement) {
		// 更新当前表对应的resultMap节点的子节点
		updateReultMapChidren(rootElement);
		// 更新当前表对应的id为ColumnList的Text
		updateColumnListText(rootElement);
		// 更新当前表对应的id为setColumnSql的子节点
		updateSetColumnSqlChidren(rootElement);
	}

	/** 更新id为setColumnSql的子节点 */
	private void updateSetColumnSqlChidren(Element rootElement) {
		Element setColumnSqlElement = findElementById(EcMyBatisSqlTypeEnum.SET_COLUMN_SQL.getDesc(), rootElement,
				EcMyBatisElementNameEnum.SELECT.getDesc());
		Element setElement = setColumnSqlElement.getChild(EcMyBatisElementNameEnum.SET.getDesc());
		if (EcBaseUtils.isNull(setElement)) {
			setElement = new Element(EcMyBatisElementNameEnum.SET.getDesc());
			setColumnSqlElement.addContent(setElement);
		}
		List<Element> ifElements = new ArrayList<>();
		for (EcGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			Element ifElement = new Element(EcMyBatisElementNameEnum.IF.getDesc());
			ifElement.setAttribute(EcMyBatisAttrKey.TEST, getTestAttrValue(data));
			ifElement.setText(EcGenerateCodeXmlUtils.getWhereDataStr(data, mybatisDTO.getTableSimpleName()));
			ifElements.add(ifElement);
		}
		setElement.removeContent();
		setElement.setContent(ifElements);
	}

	/** 更新id为columnList的Text */
	private void updateColumnListText(Element rootElement) {
		Element columnListElement = findElementById(EcMyBatisSqlTypeEnum.COLUMN_LIST.getDesc(), rootElement,
				EcMyBatisElementNameEnum.SQL.getDesc());
		columnListElement.setText(mybatisDTO.buildDatasStr());
	}

	/** 更新resultMap的子节点 */
	private void updateReultMapChidren(Element rootElement) {
		Element currentDOResultMapElement = findElementById(mybatisDTO.getSimpleClassTypeDO(), rootElement,
				EcMyBatisElementNameEnum.RESULT_MAP.getDesc());
		List<Element> currentResultMapElementChirden = currentDOResultMapElement.getChildren();
		// 保存需要不需要替换的其他节点
		List<Element> otherElements = new ArrayList<>();
		for (Element element : currentResultMapElementChirden) {
			boolean equelsResult = EcStringUtils.equals(element.getName(), EcMyBatisElementNameEnum.RESULT.getDesc());
			boolean equelsId = EcStringUtils.equals(element.getName(), EcMyBatisElementNameEnum.ID.getDesc());
			if (equelsResult || equelsId) {
				continue;
			}
			otherElements.add(element);
		}
		// 获取resultElements和idElement
		Element idElement = new Element(EcMyBatisElementNameEnum.ID.getDesc());
		List<Element> resultElements = new ArrayList<>();
		for (EcGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			String elementName = null;
			if (EcTableColumnKey.isPrimary(data.getColumnKey())) {
				elementName = EcMyBatisElementNameEnum.ID.getDesc();
			} else {
				elementName = EcMyBatisElementNameEnum.RESULT.getDesc();
			}
			Element element = new Element(elementName);
			element.setAttribute(EcMyBatisAttrKey.COLUMN, data.getColunmName());
			element.setAttribute(EcMyBatisAttrKey.JDBC_TYPE, data.getColunmType());
			element.setAttribute(EcMyBatisAttrKey.PROPERTY, data.getPropertyName());
			// 不是主键添加到result列表中
			if (EcTableColumnKey.isPrimary(data.getColumnKey())) {
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
		if (EcCollectionsUtils.isEmpty(resultMapElements)) {
			throw new RuntimeException(elementName + "元素不存在");
		}
		// 获取id为当前表所对应的resultMap
		for (Element resultMapElement : resultMapElements) {
			// 获取当前元素的指定属性
			String idValue = resultMapElement.getAttributeValue(EcMyBatisAttrKey.ID);
			if (EcStringUtils.equals(idValue, findIdValue)) {
				currentDOResultMapElement = resultMapElement;
				break;
			}
		}
		if (EcBaseUtils.isNull(currentDOResultMapElement)) {
			throw new RuntimeException("当前表对应的" + elementName + "元素不存在");
		}
		return currentDOResultMapElement;
	}

	public EcGenerateXmlMybatisBO buildRootContentElementDesc() {
		rootElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		rootElement.setElementName(EcMyBatisElementNameEnum.MAPPER.getDesc());
		rootElement.addAttribute(EcMyBatisAttrKey.NAMESPACE, mybatisDTO.getNamespace());
		rootElement.buildDataByDatabaseSources(databaseDataSources);
		super.xmlContentDocDesc.setRootElement(rootElement);
		return this;
	}

	@Override
	protected String getDocType() {
		return EcDocType.MYBATIS;
	}

	@Override
	protected EcGenerateXmlBaseDTO getGenerateXmlBaseDTO() {
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
	public EcGenerateXmlMybatisBO buildResultMap() {
		EcXmlMybatisContentElementDesc resultMapElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		resultMapElement.setElementName(EcMyBatisElementNameEnum.RESULT_MAP.getDesc());
		resultMapElement.setSqlType(EcMyBatisSqlTypeEnum.RESULT_MAP.getType());

		resultMapElement.addAttribute(EcMyBatisAttrKey.ID, mybatisDTO.getSimpleClassTypeDO());
		resultMapElement.addAttribute(EcMyBatisAttrKey.TYPE, mybatisDTO.getFullClassTypeDO());

		List<EcXmlContentElementDesc> resultMapChildes = new ArrayList<>();
		for (EcGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			EcXmlContentElementDesc elementDesc = new EcXmlContentElementDesc();
			if (EcTableColumnKey.isPrimary(data.getColumnKey())) {
				elementDesc.setElementName(EcMyBatisElementNameEnum.ID.getDesc());
			} else {
				elementDesc.setElementName(EcMyBatisElementNameEnum.RESULT.getDesc());
			}
			elementDesc.addAttribute(EcMyBatisAttrKey.COLUMN, data.getColunmName());
			elementDesc.addAttribute(EcMyBatisAttrKey.JDBC_TYPE, data.getColunmType());
			elementDesc.addAttribute(EcMyBatisAttrKey.PROPERTY, data.getPropertyName());

			resultMapChildes.add(elementDesc);
		}
		resultMapElement.addAllElement(resultMapChildes);

		rootElement.addElement(resultMapElement);
		return this;
	}

	/** 构建列的列表 */
	public EcGenerateXmlMybatisBO buildColumnList() {
		EcXmlMybatisContentElementDesc columnListElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		columnListElement.setElementName(EcMyBatisElementNameEnum.SQL.getDesc());
		columnListElement.setSqlType(EcMyBatisSqlTypeEnum.COLUMN_LIST.getType());
		columnListElement.addAttribute(EcMyBatisAttrKey.ID, getColumnList());
		columnListElement.setText(mybatisDTO.buildDatasStr());
		rootElement.addElement(columnListElement);
		return this;
	}

	/** 构建根据id查询结果 */
	public EcGenerateXmlMybatisBO buildFindById() {
		EcXmlMybatisContentElementDesc findByIdElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		findByIdElement.setElementName(EcMyBatisElementNameEnum.SELECT.getDesc());
		findByIdElement.setSqlType(EcMyBatisSqlTypeEnum.FIND_BY_ID.getType());

		findByIdElement.addAttribute(EcMyBatisAttrKey.ID, getFindById());
		findByIdElement.addAttribute(EcMyBatisAttrKey.RESULT_MAP, mybatisDTO.getSimpleClassTypeDO());
		if (mybatisDTO.getPrimaryKey() != null) {
			findByIdElement.addAttribute(EcMyBatisAttrKey.PARAMETER_TYPE,
					mybatisDTO.getPrimaryKey().getPropertyFullType());
		}
		// 构建include子节点
		EcXmlMybatisContentElementDesc includeElement = new EcXmlMybatisContentElementDesc();
		includeElement.setElementName(EcMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(EcMyBatisAttrKey.REFID, getColumnList());
		findByIdElement.addElement(includeElement);

		findByIdElement.addWhereData(mybatisDTO.getPrimaryKey());
		rootElement.addElement(findByIdElement);
		return this;
	}

	/** 构建ListCount */
	public EcGenerateXmlMybatisBO buildListCount() {
		EcXmlMybatisContentElementDesc listCountElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		listCountElement.setElementName(EcMyBatisElementNameEnum.SELECT.getDesc());
		listCountElement.setSqlType(EcMyBatisSqlTypeEnum.LIST_COUNT.getType());

		listCountElement.addAttribute(EcMyBatisAttrKey.ID, getListCount());
		listCountElement.addAttribute(EcMyBatisAttrKey.RESULT_TYPE, Integer.class.getName());
		listCountElement.addAttribute(EcMyBatisAttrKey.PARAMETER_TYPE, "map");

		rootElement.addElement(listCountElement);
		return this;
	}

	/** 构建ListPage */
	public EcGenerateXmlMybatisBO buildListPage() {
		EcXmlMybatisContentElementDesc listPageElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		listPageElement.setElementName(EcMyBatisElementNameEnum.SELECT.getDesc());
		listPageElement.setSqlType(EcMyBatisSqlTypeEnum.LIST_PAGE.getType());
		// 构建listPage元素的属性列表
		listPageElement.addAttribute(EcMyBatisAttrKey.ID, getListPage());
		listPageElement.addAttribute(EcMyBatisAttrKey.RESULT_MAP, mybatisDTO.getSimpleClassTypeDO());
		listPageElement.addAttribute(EcMyBatisAttrKey.PARAMETER_TYPE, "map");
		// 构建include子节点
		EcXmlMybatisContentElementDesc includeElement = new EcXmlMybatisContentElementDesc();
		includeElement.setElementName(EcMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(EcMyBatisAttrKey.REFID, getColumnList());
		listPageElement.addElement(includeElement);

		rootElement.addElement(listPageElement);
		return this;
	}

	/** 构建setColumnSql */
	public EcGenerateXmlMybatisBO buildSetColumnSql() {
		EcXmlMybatisContentElementDesc setColumnSqlElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		setColumnSqlElement.setElementName(EcMyBatisElementNameEnum.SELECT.getDesc());
		setColumnSqlElement.setSqlType(EcMyBatisSqlTypeEnum.SET_COLUMN_SQL.getType());
		// 设置属性
		setColumnSqlElement.addAttribute(EcMyBatisAttrKey.ID, getSetColumnSql());
		// 构建set子节点
		EcXmlMybatisContentElementDesc setElement = new EcXmlMybatisContentElementDesc();
		setElement.setElementName(EcMyBatisElementNameEnum.SET.getDesc());
		for (EcGenerateXmlMybatisData data : mybatisDTO.getDatas()) {
			if (EcIgnoreSetField.isIgnoreField(data.getPropertyName())) {
				continue;
			}
			EcXmlMybatisContentElementDesc ifElement = new EcXmlMybatisContentElementDesc();
			ifElement.setElementName(EcMyBatisElementNameEnum.IF.getDesc());
			// 设置if节点test为key的值
			ifElement.addAttribute(EcMyBatisAttrKey.TEST, getTestAttrValue(data));
			// 设置text
			ifElement.setText(getIfElementText(data));
			setElement.addElement(ifElement);
		}
		setColumnSqlElement.addElement(setElement);
		rootElement.addElement(setColumnSqlElement);
		return this;
	}

	/** 获取test属性的值 */
	private String getTestAttrValue(EcGenerateXmlMybatisData data) {
		// 设置属性
		StringBuilder testAttrValue = EcStringUtils.newStringBuilderDefault();
		testAttrValue.append(data.getPropertyName());
		testAttrValue.append(EcSymbol.EMPTY).append("!=").append(EcSymbol.EMPTY).append("null");
		return testAttrValue.toString();
	}

	/** 获取if元素的Text值 */
	private String getIfElementText(EcGenerateXmlMybatisData data) {
		return EcGenerateCodeXmlUtils.getMybatisColunmMappingPropertyByData(data, mybatisDTO.getTableSimpleName())
				+ EcSymbol.COMMA;
	}

	/** 构建save */
	public EcGenerateXmlMybatisBO buildSave() {
		EcXmlMybatisContentElementDesc saveElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		saveElement.setElementName(EcMyBatisElementNameEnum.INSERT.getDesc());
		saveElement.setSqlType(EcMyBatisSqlTypeEnum.SAVE.getType());

		saveElement.addAttribute(EcMyBatisAttrKey.ID, getSave());
		saveElement.addAttribute(EcMyBatisAttrKey.USE_GENERATED_KEYS, "true");
		saveElement.addAttribute(EcMyBatisAttrKey.PARAMETER_TYPE, mybatisDTO.getFullClassTypeDO());
		// 构建include子节点
		EcXmlMybatisContentElementDesc includeElement = new EcXmlMybatisContentElementDesc();
		includeElement.setElementName(EcMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(EcMyBatisAttrKey.REFID, getSetColumnSql());
		saveElement.addElement(includeElement);

		rootElement.addElement(saveElement);
		return this;
	}

	/** 构建update */
	public EcGenerateXmlMybatisBO buildUpdate() {
		EcXmlMybatisContentElementDesc updateElement = new EcXmlMybatisContentElementDesc(mybatisDTO);
		updateElement.setElementName(EcMyBatisElementNameEnum.UPDATE.getDesc());
		updateElement.setSqlType(EcMyBatisSqlTypeEnum.UPDATE.getType());

		updateElement.addAttribute(EcMyBatisAttrKey.ID, getUpdate());
		updateElement.addAttribute(EcMyBatisAttrKey.PARAMETER_TYPE, mybatisDTO.getFullClassTypeDO());
		// 构建include子节点
		EcXmlMybatisContentElementDesc includeElement = new EcXmlMybatisContentElementDesc();
		includeElement.setElementName(EcMyBatisElementNameEnum.INCLUDE.getDesc());
		includeElement.addAttribute(EcMyBatisAttrKey.REFID, getSetColumnSql());

		updateElement.addElement(includeElement);
		updateElement.addWhereData(mybatisDTO.getPrimaryKey());
		rootElement.addElement(updateElement);
		return this;
	}

	public String getColumnList() {
		return EcMyBatisSqlTypeEnum.COLUMN_LIST.getDesc();
	}

	public String getFindById() {
		return EcMyBatisSqlTypeEnum.FIND_BY_ID.getDesc();
	}

	public String getListCount() {
		return EcMyBatisSqlTypeEnum.LIST_COUNT.getDesc();
	}

	public String getListPage() {
		return EcMyBatisSqlTypeEnum.LIST_PAGE.getDesc();
	}

	public String getSetColumnSql() {
		return EcMyBatisSqlTypeEnum.SET_COLUMN_SQL.getDesc();
	}

	public String getSave() {
		return EcMyBatisSqlTypeEnum.SAVE.getDesc();
	}

	public String getUpdate() {
		return EcMyBatisSqlTypeEnum.UPDATE.getDesc();
	}
}
