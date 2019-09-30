package com.sika.code.batch.test.animal.convert;

import com.sika.code.batch.test.animal.AnimalDTO;
import com.sika.code.batch.test.animal.AnimalEntity;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author daiqi
 * @create 2019-09-12 23:15
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalConvert extends BaseConvert<AnimalEntity, AnimalDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    AnimalConvert INSTANCE = Mappers.getMapper(AnimalConvert.class);
}
