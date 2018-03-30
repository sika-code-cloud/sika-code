package com.dq.easy.cloud.module.common.generator.code.xml.utils;

import java.util.ArrayList;
import java.util.List;

import com.dq.easy.cloud.module.common.collections.utils.DqCollectionsUtils;
import com.dq.easy.cloud.module.common.generator.code.xml.constant.DqCodeGenerateXmlConstant.DqMyBatisAttrKey;
import com.dq.easy.cloud.module.common.generator.code.xml.pojo.dto.DqGenerateXmlMybatisData;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;

public class DqGenerateCodeXmlUtils {
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
	public static String getMybatisColunmMappingPropertyByData(DqGenerateXmlMybatisData generateXmlMybatisData, String simpleTableName) {
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		sb.append(simpleTableName).append(DqSymbol.STOP).append(generateXmlMybatisData.getColunmName());
		sb.append(DqSymbol.EMPTY).append(DqSymbol.EQUAL).append(DqSymbol.EMPTY);
		sb.append(DqSymbol.NUMBER).append(DqSymbol.LEFT_BRACES);
		sb.append(generateXmlMybatisData.getPropertyName()).append(DqSymbol.COMMA).append(DqSymbol.EMPTY);
		sb.append(DqMyBatisAttrKey.JDBC_TYPE).append(DqSymbol.EQUAL).append(generateXmlMybatisData.getColunmType());
		sb.append(DqSymbol.RIGHT_BRACES);
		return sb.toString();
	}
	
	/** 获取where数据列表的字符串 */
	public static String getWhereDataStr(DqGenerateXmlMybatisData data, String tableSimpleName) {
		List<DqGenerateXmlMybatisData> datas = new ArrayList<>();
		datas.add(data);
		return getWhereDatasStr(datas, tableSimpleName);
	}
	
	/** 获取where数据列表的字符串 */
	public static String getWhereDatasStr(List<DqGenerateXmlMybatisData> datas, String tableSimpleName) {
		if (DqCollectionsUtils.isEmpty(datas)) {
			return null;
		}
		StringBuilder sb = DqStringUtils.newStringBuilderDefault();
		for (int i = 0; i < datas.size(); ++i) {
			DqGenerateXmlMybatisData data = datas.get(i);
			sb.append(DqGenerateCodeXmlUtils.getMybatisColunmMappingPropertyByData(data, tableSimpleName));
			if (i < datas.size() - 1) {
				sb.append("AND");
			}
		}
		return sb.toString();
	}
}
