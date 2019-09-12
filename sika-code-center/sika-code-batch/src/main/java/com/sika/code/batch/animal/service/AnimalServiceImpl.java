package com.sika.code.batch.animal.service;

import com.sika.code.batch.animal.AnimalDTO;
import com.sika.code.batch.animal.AnimalEntity;
import com.sika.code.batch.animal.convert.AnimalConvert;
import com.sika.code.batch.animal.mapper.AnimalMapper;
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
