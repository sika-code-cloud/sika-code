package com.sika.code.expand;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.Data;

/**
 * @author daiqi
 * @create 2019-05-07 13:56
 */
@Data
public class TableInfoExpand extends TableInfo {
    /** 包主体名称 */
    private String classBodyName;
    /** 移除前缀的表明 */
    private String nameRemovePrefix;
    /** 父包名 */
    private String parentPackage;
    /** 模块名称 */
    private String modulePackage;

}
