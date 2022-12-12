package com.sika.code.generator.dto;

import cn.hutool.core.util.StrUtil;
import com.sika.code.generator.constant.GenerratorClassEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author daiqi
 * @create 2021-10-28 0:12
 */
@Data
@Slf4j
public class GeneratorDTO {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String tableName;
    private String author;

    private String tablePrefix;
    private String tableSuffix;
    private String infrastructureDbOutDir;
    private String infrastructureCommonOutDir;
    private String mapperXmlOutDir;
    private String domainOutDir;
    private String interfacesRestOutDir;
    private String interfacesRpcApiOutDir;
    private String interfacesRpcImplOutDir;
    private String applicationOutDir;

    private String testRestOutDir;
    private String testDomainOutDir;
    private String infrastructureParent;
    private String infrastructureDbParent;
    private String domainParent;
    private String interfacesRestParent;
    private String interfacesRpcApiParent;
    private String interfacesRpcImplParent;
    private String applicationParent;
    private String testRestParent;
    private String testDomainParent;

    private Boolean fileOverride;
    private Class<?> primaryKeyClass = Long.class;

    /** 需要忽略的自动生成的类 */
    private List<GenerratorClassEnum> ignoreClass;

    private Boolean generateController = true;
    private Boolean generateDomain = true;

    private String applicationClassName;
    private String applicationSimpleName;
    private String [] ignoreColumns = {"id", "create_date", "update_date",
            "version", "available", "is_deleted", "remark"};

    public String getTablePrefix() {
        if (tablePrefix == null) {
            return StrUtil.EMPTY;
        }
        return tablePrefix;
    }

    public String getTableSuffix() {
        if (tableSuffix == null) {
            return StrUtil.EMPTY;
        }
        return tableSuffix;
    }

    public Boolean getFileOverride() {
        if (fileOverride == null) {
            return false;
        }
        return fileOverride;
    }

    public List<GenerratorClassEnum> getIgnoreClass() {
        if (ignoreClass == null) {
            ignoreClass = Lists.newArrayList();
        }
        return ignoreClass;
    }

    public void setIgnoreClass(List<GenerratorClassEnum> ignoreClass) {
        if (ignoreClass == null) {
            return;
        }
        this.ignoreClass = ignoreClass;
    }
}
