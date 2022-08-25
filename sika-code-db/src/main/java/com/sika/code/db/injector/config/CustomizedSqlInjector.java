package com.sika.code.db.injector.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.sika.code.db.injector.method.LogicDeleteMethod;
import com.sika.code.db.injector.method.UpdateBatchMethod;

import java.util.List;

public class CustomizedSqlInjector extends DefaultSqlInjector {
    /**
     * 如果只需增加方法，保留mybatis plus自带方法，
     * 可以先获取super.getMethodList()，再添加add
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new UpdateBatchMethod());
        methodList.add(new LogicDeleteMethod());
        return methodList;
    }
}
