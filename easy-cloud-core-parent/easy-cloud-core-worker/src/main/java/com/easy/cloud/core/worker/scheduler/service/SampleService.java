package com.easy.cloud.core.worker.scheduler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private static final Logger LOG = LoggerFactory.getLogger(SampleService.class);

    public void hello() {
        LOG.info("Hello World!");
    }
}