package com.sika.code.database.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sika.code.basic.util.BaseUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础对象填充助手类
 *
 * @author daiqi
 * @create 2019-05-09 9:42
 */
public abstract class BaseMetaObjectHandler<BY extends Serializable> implements MetaObjectHandler {
    /**
     * entity创建时间的名称 --- createDate
     */
    protected static final String CREATE_DATE_NAME = "createDate";
    /**
     * entity创建者的名称 --- createDate
     */
    protected static final String CREATE_BY_NAME = "createBy";
    /**
     * entity更新时间的名称 --- createDate
     */
    protected static final String UPDATE_DATE_NAME = "updateDate";
    /**
     * entity更新者的名称 --- updateBy
     */
    protected static final String UPDATE_BY_NAME = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByNameCustomer(getCreateDateName(), new Date(), metaObject);
        this.setFieldValByNameCustomer(getUpdateDateName(), new Date(), metaObject);
        this.setFieldValByNameCustomer(getCreateByName(), getCreateBy(), metaObject);
        this.setFieldValByNameCustomer(getUpdateByName(), getUpdateBy(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByNameCustomer(getUpdateDateName(), new Date(), metaObject);
        this.setFieldValByNameCustomer(getUpdateByName(), getUpdateBy(), metaObject);;
    }

    /**  
     * <p>
     * 使用该方法进行填充，若目标对象的fieldName对应的属性已经有值则不会进行填充
     * </p>
     *   
     * @param fieldName : 属性名称
     * @param fieldVal : 需要填充的值
     * @param metaObject : 封装entity的对象
     * @return com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
     * @author daiqi 
     * @date 2019/6/30 12:17
     */  
    protected MetaObjectHandler setFieldValByNameCustomer(String fieldName, Object fieldVal, MetaObject metaObject) {
        Object targetEntityValue = getFieldValByName(fieldName, metaObject);
        if (BaseUtil.isNotNull(targetEntityValue)) {
            return this;
        }
        return this.setFieldValByName(fieldName, fieldVal, metaObject);
    }

    /**
     * 获取创建时间的名称
     */
    public String getCreateDateName() {
        return CREATE_DATE_NAME;
    }

    /**
     * 获取创建者的名称
     */
    public String getCreateByName() {
        return CREATE_BY_NAME;
    }

    /**
     * 获取更新时间的名称
     */
    public String getUpdateDateName() {
        return UPDATE_DATE_NAME;
    }

    /**
     * 获取更新者的名称
     */
    public String getUpdateByName() {
        return UPDATE_BY_NAME;
    }

    /**
     * <p>
     * 获取创建者
     * </p>
     *
     * @return BY
     * @author daiqi
     * @date 2019/5/13 9:49
     */
    public abstract BY getCreateBy();

    /**
     * <p>
     * 获取更新者
     * </p>
     *
     * @return BY
     * @author daiqi
     * @date 2019/5/13 9:49
     */
    public abstract BY getUpdateBy();
}
