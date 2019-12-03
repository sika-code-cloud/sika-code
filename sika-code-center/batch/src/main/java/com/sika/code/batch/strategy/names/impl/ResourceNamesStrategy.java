package com.sika.code.batch.strategy.names.impl;

import com.google.common.collect.Lists;
import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.batch.strategy.names.NamesStrategy;
import com.sika.code.common.string.util.StringUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * 名称策略 -- 来自于资源
 *
 * @author daiqi
 * @create 2019-12-03 23:42
 */
@Data
@Accessors(chain = true)
@Slf4j
public class ResourceNamesStrategy implements NamesStrategy<ResourceNamesStrategy> {
    private static final int DEFAULT_NAMES_ON_LINE = 1;
    /**
     * 资源
     */
    private Resource resource;
    /**
     * 名称所在的行
     */
    private int namesOnLine;
    /**
     * 分隔符
     */
    private String delimiter;
    /**
     * 名称
     */
    private List<String> names;


    @Override
    public ResourceNamesStrategy build() {
        // 初始化
        if (namesOnLine < DEFAULT_NAMES_ON_LINE) {
            this.namesOnLine = DEFAULT_NAMES_ON_LINE;
        }
        // 校验
        Assert.verifyObjNull(resource, "资源[resource]");
        Assert.verifyStrEmpty(delimiter, "分隔符[delimiter]");
        // 构建名称
        try {
            buildNames();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * 构建名称
     */
    protected void buildNames() throws Exception {
        File file = resource.getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        int currentLine = DEFAULT_NAMES_ON_LINE;
        while ((line = bufferedReader.readLine()) != null) {
            if (namesOnLine != currentLine) {
                currentLine++;
                continue;
            }
            if (StringUtil.isBlank(line)) {
                throw new RuntimeException(resource.getFilename() + " 第【" + currentLine + "】行为空，映射为names失败");
            }
            this.names = Lists.newArrayList(StringUtil.split(line, delimiter));
            return;
        }

    }
}
