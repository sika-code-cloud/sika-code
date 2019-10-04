package com.sika.code.batch.test.animal.listen;

import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-09-18 23:34
 */
@Slf4j(topic = "animal-listener")
public class AnimalListener {

    public static class AnimalStepExecutionListener implements StepExecutionListener {

        private static Long beginTime = 0L;

        @Override
        public ExitStatus afterStep(StepExecution stepExecution) {
            LogUtil.info("afterStep", stepExecution.toString(), log);
            log.info("-------------执行的时间为:{}-------------", (System.currentTimeMillis() - beginTime));
            return stepExecution.getExitStatus();
        }

        @Override
        public void beforeStep(StepExecution stepExecution) {
            beginTime = System.currentTimeMillis();
            LogUtil.info("beforeStep", stepExecution.toString(), log);

        }

    }

    public static class AnimalChunkListener implements ChunkListener {
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

    public static class AnimalItemReadListener<I> implements ItemReadListener<I> {
        @Override
        public void afterRead(I item) {
            LogUtil.info("afterRead", item, log);
        }

        @Override
        public void beforeRead() {
            LogUtil.info("beforeRead", null, log);
        }

        @Override
        public void onReadError(Exception ex) {
            LogUtil.error("onReadError", ex, log);
        }

    }

    public static class AnimalItemProcessListener<I, O> implements ItemProcessListener<I, O> {

        @Override
        public void afterProcess(I item, O result) {
            LogUtil.info("afterProcess：item", item, log);
            LogUtil.info("afterProcess：result", result, log);
        }

        @Override
        public void beforeProcess(I item) {
            LogUtil.info("beforeProcess：item", item, log);
        }

        @Override
        public void onProcessError(I item, Exception e) {
            LogUtil.info("onProcessError：item", item, log);
            LogUtil.error("onProcessError：Exception", e, log);
        }
    }

    public static class AnimalItemWriteListener<O> implements ItemWriteListener<O> {

        @Override
        public void afterWrite(List<? extends O> items) {
            LogUtil.info("afterWrite", items, log);
        }

        @Override
        public void beforeWrite(List<? extends O> items) {
            LogUtil.info("beforeWrite", items, log);
        }

        @Override
        public void onWriteError(Exception exception, List<? extends O> items) {
            LogUtil.error("onWriteError：exception", exception, log);
            LogUtil.error("onWriteError：items", items, log);
        }

    }

    public static class AnimalSkipListener<I, O> implements SkipListener<I, O> {
        @Override
        public void onSkipInProcess(I item, Throwable t) {
            LogUtil.error("onSkipInProcess：item", item, log);
            LogUtil.error("onSkipInProcess：Throwable", t, log);
        }

        @Override
        public void onSkipInRead(Throwable t) {
            LogUtil.error("onSkipInRead：Throwable", t, log);
        }

        @Override
        public void onSkipInWrite(O item, Throwable t) {
            LogUtil.error("onSkipInWrite：item", item, log);
            LogUtil.error("onSkipInWrite：Throwable", t, log);
        }

    }
}
