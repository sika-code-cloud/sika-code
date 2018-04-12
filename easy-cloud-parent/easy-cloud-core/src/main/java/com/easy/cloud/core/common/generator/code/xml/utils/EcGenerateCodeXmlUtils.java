package com.easy.cloud.core.common.generator.code.xml.utils;

import java.util.ArrayList;
import java.util.List;

import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.generator.code.xml.constant.EcCodeGenerateXmlConstant.EcMyBatisAttrKey;
import com.easy.cloud.core.common.generator.code.xml.pojo.dto.EcGenerateXmlMybatisData;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

public class EcGenerateCodeXmlUtils {
	/**
	 * 
	 * <p>
	 * 根据数据获取mybatis列名和属性映射关系字符串
	 * </p>
	 *
	 * @param generateXmlMybatisData
	 * @param simpleTableName
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月30日 上午11:54:10
	 */
	public static String getMybatisColunmMappingPropertyByData(EcGenerateXmlMybatisData generateXmlMybatisData, String simpleTableName) {
		StringBuilder sb = EcStringUtils.newStringBuilderDefault();
		sb.append(simpleTableName).append(EcSymbol.STOP).append(generateXmlMybatisData.getColunmName());
		sb.append(EcSymbol.SPACE).append(EcSymbol.EQUAL).append(EcSymbol.SPACE);
		sb.append(EcSymbol.NUMBER).append(EcSymbol.LEFT_BRACES);
		sb.append(generateXmlMybatisData.getPropertyName()).append(EcSymbol.COMMA).append(EcSymbol.SPACE);
		sb.append(EcMyBatisAttrKey.JDBC_TYPE).append(EcSymbol.EQUAL).append(generateXmlMybatisData.getColunmType());
		sb.append(EcSymbol.RIGHT_BRACES);
		return sb.toString();
	}
	
	/** 获取where数据列表的字符串 */
	public static String getWhereDataStr(EcGenerateXmlMybatisData data, String tableSimpleName) {
		List<EcGenerateXmlMybatisData> datas = new ArrayList<>();
		datas.add(data);
		return getWhereDatasStr(datas, tableSimpleName);
	}
	
	/** 获取where数据列表的字符串 */
	public static String getWhereDatasStr(List<EcGenerateXmlMybatisData> datas, String tableSimpleName) {
		if (EcCollectionsUtils.isEmpty(datas)) {
			return null;
		}
		StringBuilder sb = EcStringUtils.newStringBuilderDefault();
		for (int i = 0; i < datas.size(); ++i) {
			EcGenerateXmlMybatisData data = datas.get(i);
			sb.append(EcGenerateCodeXmlUtils.getMybatisColunmMappingPropertyByData(data, tableSimpleName));
			if (i < datas.size() - 1) {
				sb.append("AND");
			}
		}
		return sb.toString();
	}
}
