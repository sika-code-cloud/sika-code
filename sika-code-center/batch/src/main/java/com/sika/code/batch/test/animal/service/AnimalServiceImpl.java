package com.sika.code.batch.test.animal.service;

import com.sika.code.batch.test.animal.AnimalDTO;
import com.sika.code.batch.test.animal.AnimalEntity;
import com.sika.code.batch.test.animal.convert.AnimalConvert;
import com.sika.code.batch.test.animal.mapper.AnimalMapper;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author daiqi
 * @create 2019-09-12 23:11
 */
@Service
public class AnimalServiceImpl extends BaseStandardServiceImpl<AnimalMapper, AnimalEntity, AnimalDTO> implements AnimalService {

    @Override
    protected BaseConvert<AnimalEntity, AnimalDTO> convert() {
        return AnimalConvert.INSTANCE;
    }
}
