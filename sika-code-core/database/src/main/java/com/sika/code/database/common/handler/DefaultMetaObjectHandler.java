package com.sika.code.database.common.handler;

import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;

/**
 * 默认的对象填充助手类
 *
 * @author daiqi
 * @create 2019-05-13 9:41
 */
public class DefaultMetaObjectHandler extends BaseMetaObjectHandler<Serializable> {
    @Override
    public void insertFill(MetaObject metaObject) {
        super.insertFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        super.updateFill(metaObject);
    }

    @Override
    public Serializable getCreateBy() {
        return null;
    }

    @Override
    public Serializable getUpdateBy() {
        return null;
    }
}
