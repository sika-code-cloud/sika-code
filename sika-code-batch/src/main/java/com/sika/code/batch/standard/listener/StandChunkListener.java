package com.sika.code.batch.standard.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

import java.util.Map;

/**
 * <p>
 *  批处理监听器
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/10/2 10:04
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandChunkListener implements ChunkListener {

    protected Map<String, Object> contextMap;
    @Override
    public void beforeChunk(ChunkContext chunkContext) {

    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {

    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {

    }
}
