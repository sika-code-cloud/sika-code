package com.sika.code.standard.footer.demo.common.thirdpart.erp.convert;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author daiqi
 * @create 2019-04-05 9:23
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ErpConvert {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ErpConvert INSTANCE = Mappers.getMapper(ErpConvert.class);
}
