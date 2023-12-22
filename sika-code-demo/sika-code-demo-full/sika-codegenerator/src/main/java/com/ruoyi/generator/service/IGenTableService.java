package com.ruoyi.generator.service;

import java.util.List;
import java.util.Map;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.Starter;

/**
 * 业务 服务层
 * 
 * @author ruoyi
 */
public interface IGenTableService
{

    byte[] downloadProjCode(Starter starter, List<GenTable> genTables);

}
