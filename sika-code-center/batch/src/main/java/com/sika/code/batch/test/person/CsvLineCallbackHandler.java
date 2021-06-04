package com.sika.code.batch.test.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.LineCallbackHandler;

/**
 * @author daiqi
 * @create 2019-09-23 23:39
 */
@Slf4j
public class CsvLineCallbackHandler implements LineCallbackHandler {
    @Override
    public void handleLine(String line) {
        log.info("----------------lineCallback------------{}", line);
    }
}
