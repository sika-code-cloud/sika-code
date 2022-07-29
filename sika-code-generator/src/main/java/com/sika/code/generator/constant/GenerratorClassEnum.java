package com.sika.code.generator.constant;

import com.sika.code.core.base.constant.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代码生成器的类型
 *
 * @author daiqi
 * @create 2021-11-30 0:31
 */
@AllArgsConstructor
@Getter
public enum GenerratorClassEnum implements BaseTypeEnum<Integer> {
    /** 基础设施类 */
    PO(101, "持久化类"),
    DOT(102, "DTO类"),
    QUERY(103, "查询类"),
    VALUE_OBJECT(104, "值类"),
    CONVERT(105, "转化类"),
    /** 基础设施DB类 */
    MAPPER(201, "mybatis映射类"),
    XML(202, "XML类"),
    /** 领域类 */
    REPOSITORY(301, "持久化类"),
    ENTITY(302, "实体类"),
    FACTORY(303, "工厂类"),
    AGGREGATE_ROOT(304, "聚合根类"),
    /** 应用类 */
    SERVICE_APPLICATION(404, "应用服务类"),
    /** 接口类 */
    CONTROLLER(501, "rest控制器类"),
    RPC_API(502, "RPC接口类"),
    RPC_IMP(503, "RPC实现类");


    private final Integer type;
    private final String desc;

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
