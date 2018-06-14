package com.easy.cloud.core.reptile.common.utils;

import com.easy.cloud.core.basic.constant.EcBaseConstant.EcAvailableEnum;
import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.reptile.common.config.EcReptileConfig;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldTypeEnum;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldValueSourceEnum;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant.EcDataFieldValueSourceTypeEnum;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileDataDTO;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileKeyValueDTO;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.engine.pojo.dto.EcReptileEngineBeanClassDTO;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicField;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.dynamic.JavassistDynamicBean;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.utils.UrlMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 爬虫工具类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月7日 下午6:44:00
 */
public class EcReptileUtils {

    /**
     * <p>
     * 将要爬取数据的url放到Scheduler中
     * </p>
     * <p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param geccoEngine    : GeccoEngine : 爬虫引擎
     * @param dynamicBeanDTO : EcReptileDynamicBeanDTO : 爬虫动态bean数据传输对象
     * @param dataDTO        : EcReptileDataDTO : 爬虫数据的数据传输对象由业务系统传入
     * @author daiqi
     * @创建时间 2018年6月8日 下午3:07:08
     */
    public static void intoScheduler(GeccoEngine geccoEngine, EcReptileDynamicBeanDTO dynamicBeanDTO,
                                     EcReptileDataDTO dataDTO) {
        List<String> matchUrls = dynamicBeanDTO.getMatchUrlList();
        if (EcCollectionsUtils.isEmpty(matchUrls)) {
            throw new EcBaseBusinessException("设置异常", "待设置异常");
        }
        for (String matchUrl : matchUrls) {
            String intoUrl = matchUrl;
            if (EcCollectionsUtils.isEmpty(dataDTO.getUrlKeyValueDTOs())) {
                geccoEngine.getScheduler().into(new HttpGetRequest(intoUrl));
                continue;
            }
            for (EcReptileKeyValueDTO keyValueDTO : dataDTO.getUrlKeyValueDTOs()) {
                intoUrl = UrlMatcher.replaceParams(intoUrl, keyValueDTO.getKey(), keyValueDTO.getValue());
            }
            geccoEngine.getScheduler().into(new HttpGetRequest(intoUrl));
        }
    }
    /**
     * <p>
     * 更新爬虫引擎的规则
     * </p>
     * <p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param dynamicBeanDTOs
     * @return
     * @author daiqi
     * @创建时间 2018年6月7日 下午6:45:26
     */
    public static void updateReptileEngineRule(List<EcReptileDynamicBeanDTO> dynamicBeanDTOs) {
        EcReptileUtils.unLoadReptileRuleClass();
        for (EcReptileDynamicBeanDTO dynamicBeanDTO : dynamicBeanDTOs) {
            // 加载爬虫beanClass类到内存
            EcReptileUtils.loadReptileEngineRule(dynamicBeanDTO);
        }
    }

    /**
     * <p>
     * 加载爬虫引擎的规则
     * </p>
     * <p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param dynamicBeanDTO
     * @return
     * @author daiqi
     * @创建时间 2018年6月7日 下午6:45:26
     */
    public static void loadReptileEngineRule(EcReptileDynamicBeanDTO dynamicBeanDTO) {
        // 构建JavassistDynamicBean
        JavassistDynamicBean dynamicBean = buildJavassistDynamicBean(dynamicBeanDTO);
        // 构建规则属性列表
        buildDynamicField(dynamicBean, dynamicBeanDTO);
        // 加载class
        loadReptileRuleClass(dynamicBean);
    }

    /**
     * <p>
     * 加载爬虫规则class
     * </p>
     *
     * @param javassistDynamicBean
     * @return void
     * @author daiqi
     * @date 2018/6/13 14:06
     */
    private synchronized static void loadReptileRuleClass(JavassistDynamicBean javassistDynamicBean) {
        Class<?> ruleBeanClazz = javassistDynamicBean.loadClass();
        EcReptileConfig.getReptileEngineBeanClassDTO().addJavassistDynamicBean(javassistDynamicBean);
        EcReptileConfig.getReptileEngineBeanClassDTO().putRuleBeanClazz(ruleBeanClazz);
    }

    /**
     * <p>
     * 从引擎中下线相关规则
     * </p>
     *
     * @return void
     * @author daiqi
     * @date 2018/6/12 17:04
     */
    public synchronized static void unLoadReptileRuleClass() {
        EcReptileEngineBeanClassDTO reptileEngineBeanClassDTO = EcReptileConfig.getReptileEngineBeanClassDTO();
        for (JavassistDynamicBean javassistDynamicBean : reptileEngineBeanClassDTO.getJavassistDynamicBeans()) {
            javassistDynamicBean.unloadClass();
        }
        reptileEngineBeanClassDTO.clearJavassistDynamicBeans();
        reptileEngineBeanClassDTO.clearRuleBeanClazzs();
    }

    /**
     * <p>
     * 构建动态bean的动态数据属性
     * </p>
     * <p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param dynamicBean
     * @param dynamicBeanDTO
     * @author daiqi
     * @创建时间 2018年6月7日 下午8:13:04
     */
    private static void buildDynamicField(JavassistDynamicBean dynamicBean, EcReptileDynamicBeanDTO dynamicBeanDTO) {
        for (EcReptileDataFieldDTO dataFieldDTO : dynamicBeanDTO.getReptileDataFieldDTOs()) {
            if (EcBaseUtils.equals(dataFieldDTO.getAvailable(), EcAvailableEnum.YES.type())) {
                DynamicField dynamicField = buildDynamicFieldName(dynamicBean, dataFieldDTO);
                dynamicField = buildDynamicValueSourceType(dynamicField, dynamicBean, dataFieldDTO);
                dynamicField = buildDynamicValueSource(dynamicField, dynamicBean, dataFieldDTO);
                dynamicField.build();
            }
        }
    }

    private static DynamicField buildDynamicValueSourceType(DynamicField dynamicField, JavassistDynamicBean dynamicBean,
                                                            EcReptileDataFieldDTO dataFieldDTO) {
        if (EcBaseUtils.equals(dataFieldDTO.getValueSourceType(), EcDataFieldValueSourceTypeEnum.CSS_PATH.type())) {
            dynamicField.csspath(dataFieldDTO.getPath());
        } else if (EcBaseUtils.equals(dataFieldDTO.getValueSourceType(), EcDataFieldValueSourceTypeEnum.REQUEST_PARAMETER.type())) {
            dynamicField.requestParameter();
        } else if (EcBaseUtils.equals(dataFieldDTO.getValueSourceType(), EcDataFieldValueSourceTypeEnum.REQUEST.type())) {
            dynamicField.request();
        } else {
            dynamicField.ajax(dataFieldDTO.getPath());
        }
        return dynamicField;
    }

    private static DynamicField buildDynamicValueSource(DynamicField dynamicField, JavassistDynamicBean dynamicBean,
                                                        EcReptileDataFieldDTO dataFieldDTO) {
        int valueSourceType = dataFieldDTO.getValueSource();
        if (valueSourceType == EcDataFieldValueSourceEnum.TEXT.type()) {
            dynamicField.text(dataFieldDTO.isValueTrue());
        } else if (valueSourceType == EcDataFieldValueSourceEnum.HTML.type()) {
            dynamicField.html(dataFieldDTO.isValueTrue());
        } else if (valueSourceType == EcDataFieldValueSourceEnum.HREF.type()) {
            dynamicField.href(dataFieldDTO.isValueTrue());
        } else if (valueSourceType == EcDataFieldValueSourceEnum.IMAGE.type()) {
            if (EcStringUtils.isNotEmpty(dataFieldDTO.getDownloadPath())) {
                dynamicField.image(dataFieldDTO.getDownloadPath(), dataFieldDTO.getFieldValue());
            } else {
                dynamicField.image();
            }
        } else if (valueSourceType == EcDataFieldValueSourceEnum.ATTR.type()) {
            dynamicField.attr(dataFieldDTO.getFieldValue());
        } else if (valueSourceType == EcDataFieldValueSourceEnum.AJAX.type()) {
            dynamicField.ajax(dataFieldDTO.getFieldValue());
        }
        return dynamicField;
    }

    /**
     * <p>
     * 根据动态属性的名称
     * </p>
     * <p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     *     dataFieldDTO.fieldType : int : 属性类型 ：是
     *     dataFieldDTO.fieldName : userName : 属性名称 ：是
     * </pre>
     *
     * @param dynamicBean  : 动态bean
     * @param dataFieldDTO : 数据属性数据传输对象
     * @return
     * @author daiqi
     * @创建时间 2018年6月7日 下午8:14:25
     */
    private static DynamicField buildDynamicFieldName(JavassistDynamicBean dynamicBean,
                                                      EcReptileDataFieldDTO dataFieldDTO) {
        String fieldName = dataFieldDTO.getFieldName();
        String fieldType = dataFieldDTO.getFieldType();
        DynamicField dynamicField;
        if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.LIST.type())) {
            if (EcStringUtils.isNotEmpty(dataFieldDTO.getSubFieldClassName())) {
                Class<?> ruleClass = EcReptileConfig.getReptileEngineBeanClassDTO().getRuleBeanClazz(dataFieldDTO.getSubFieldClassName());
                dynamicField = dynamicBean.listField(fieldName, ruleClass);
            } else {
                dynamicField = dynamicBean.listField(fieldName, Object.class);
            }
        } else if (EcStringUtils.equalsIgnoreCase(fieldType, EcDataFieldTypeEnum.REQUEST.type())) {
            dynamicField = dynamicBean.requestField(fieldName);
        } else {
            dynamicField = dynamicBean.field(fieldName, Object.class);
        }
        return dynamicField;
    }

    /**
     * <p>
     * 构建动态JavassistDynamicBean对象
     * </p>
     * <p>
     * <pre>
     *     所需参数示例及其说明
     *     参数名称 : 示例值 : 说明 : 是否必须
     * </pre>
     *
     * @param dynamicBeanDTO
     * @return
     * @author daiqi
     * @创建时间 2018年6月7日 下午7:14:00
     */
    private static JavassistDynamicBean buildJavassistDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO) {
        if (EcCollectionsUtils.isEmpty(dynamicBeanDTO.getMatchUrlList())) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.LIST_CANT_NULL, "matchUrlList");
        }

        if (EcCollectionsUtils.isEmpty(dynamicBeanDTO.getPipelineNameList())) {
            throw new EcBaseBusinessException(EcBaseErrorCodeEnum.LIST_CANT_NULL, "pipelineNameList");
        }
        JavassistDynamicBean dynamicBean;
        if (EcStringUtils.isNotEmpty(dynamicBeanDTO.getBeanClassNameBody())
                && EcStringUtils.isNotEmpty(dynamicBeanDTO.getBeanClassFullName())) {
            dynamicBean = DynamicGecco.html(dynamicBeanDTO.getBeanClassFullName());
        } else {
            dynamicBean = DynamicGecco.html();
        }
        List<String> matchUrls = dynamicBeanDTO.getMatchUrlList();
        // 获取matchUrl
        List<String> pipelineNameList = dynamicBeanDTO.getPipelineNameList();
        dynamicBean.gecco(matchUrls.toArray(new String[matchUrls.size()]), pipelineNameList.toArray(new String[pipelineNameList.size()]));
        return dynamicBean;
    }
}
