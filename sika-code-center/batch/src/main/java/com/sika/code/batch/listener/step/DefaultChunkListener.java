package com.sika.code.batch.listener.step;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author daiqi
 * @create 2019-10-04 22:30
 */
@Slf4j
public class DefaultChunkListener implements ChunkListener {
    @Override
    public void afterChunk(ChunkContext context) {
        LogUtil.info("afterChunk", context.toString(), log);
    }

    @Override
    public void beforeChunk(ChunkContext context) {
        LogUtil.info("beforeChunk", context.toString(), log);
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        LogUtil.error("afterChunkError：context", context.toString(), log);
    }
}
