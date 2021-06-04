package com.sika.code.batch.service;

import com.sika.code.basic.service.BaseService;
import com.sika.code.batch.test.animal.AnimalEntity;
import com.sika.code.batch.test.animal.mapper.AnimalMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author daiqi
 * @create 2020-01-09 23:26
 */
@Service
public class AnimalService extends BaseService<AnimalMapper, AnimalEntity> {
    @Override
    public boolean saveBatch(Collection<AnimalEntity> entityList) {
        return saveBatch(entityList, 1000);
    }
}
