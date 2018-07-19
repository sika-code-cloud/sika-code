package com.easy.cloud.core.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author daiqi
 * @create 2018-07-19 15:23
 */
@Component
public class ApplicationContextMetrics implements PublicMetrics {
    @Autowired
    private ApplicationContext context;
    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new ArrayList<>();
        metrics.add(new Metric<Long>("spring.context.startup-date",
                context.getStartupDate()));
        metrics.add(new Metric<Integer>("spring.beans.definitions",
                context.getBeanDefinitionCount()));
        metrics.add(new Metric<Integer>("spring.beans",
                context.getBeanNamesForType(Object.class).length));
        metrics.add(new Metric<Integer>("spring.controllers",
                context.getBeanNamesForAnnotation(Controller.class).length));
        metrics.add(new Metric<Integer>("spring.services",
                context.getBeanNamesForAnnotation(Service.class).length));
        return metrics;
    }
}
