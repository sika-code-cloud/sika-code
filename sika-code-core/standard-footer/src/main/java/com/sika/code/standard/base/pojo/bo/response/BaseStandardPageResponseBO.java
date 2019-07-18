package com.sika.code.standard.base.pojo.bo.response;

import com.sika.code.database.common.Page;

/**
 * 标准框架基础分页响应类，所有分页响应类必须实现该接口
 *
 * @author daiqi
 * @create 2018-09-25 12:39
 */
public interface BaseStandardPageResponseBO<POJO, PageItem> extends BaseStandardResponseBO<POJO> {
    /**
     * <p>
     * 数据分页
     * </p>
     *
     * @return java.manager.Page<PageItem>
     * @author daiqi
     * @date 2019/5/10 11:43
     */
    Page<PageItem> getPage();

}
