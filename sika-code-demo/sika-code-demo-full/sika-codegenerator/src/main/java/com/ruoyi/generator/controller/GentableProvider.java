package com.ruoyi.generator.controller;

import com.ruoyi.generator.domain.GenTable;

import java.util.List;

public interface GentableProvider {

    public List<GenTable> getGenTable(String sql, String ignorePrefix) throws Exception;
}
