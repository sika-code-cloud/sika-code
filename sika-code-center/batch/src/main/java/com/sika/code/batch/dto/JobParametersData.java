package com.sika.code.batch.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-09-29 23:17
 */
@Data
@ToString
@Accessors(chain = true)
public class JobParametersData<From, To> {
    private ItemReader<From> itemReader;
    private ItemProcessor<From, To> itemProcessor;
    private ItemWriter<To> itemWriter;
    private List<String> names;
    private String delimiter;
    private Class<From> fromClass;
    private Class<To> toClass;
    private Resource resource;
}
