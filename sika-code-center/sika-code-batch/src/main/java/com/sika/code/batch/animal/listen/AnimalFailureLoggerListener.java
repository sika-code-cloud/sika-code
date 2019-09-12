package com.sika.code.batch.animal.listen;

import com.sika.code.batch.animal.AnimalDTO;
import com.sika.code.batch.animal.AnimalEntity;
import com.sika.code.common.json.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.listener.ItemListenerSupport;

import java.util.List;

/**
 * 经测试没有作用
 * @author daiqi
 * @create 2019-09-12 23:59
 */
@Slf4j(topic = "animal")
public class AnimalFailureLoggerListener extends ItemListenerSupport<AnimalDTO, AnimalEntity> {

    @Override
    public void onReadError(Exception ex) {
        log.error(ex.getMessage(), ex);
        super.onReadError(ex);
    }

    @Override
    @AfterChunkError
    public void onWriteError(Exception ex, List<? extends AnimalEntity> item) {
        super.onWriteError(ex, item);
        log.error(ex.getMessage(), ex);
        log.info("------------error-------{}", JSONUtil.toJSONString(item));
    }
}
