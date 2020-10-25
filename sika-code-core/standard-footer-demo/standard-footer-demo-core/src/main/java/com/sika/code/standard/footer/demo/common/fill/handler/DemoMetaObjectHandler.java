package com.sika.code.standard.footer.demo.common.fill.handler;

import com.sika.code.standard.fill.handler.StandardMetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author daiqi
 * @create 2019-05-29 17:40
 */
@Slf4j
public class DemoMetaObjectHandler extends StandardMetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        super.insertFill(metaObject);
    }

}

