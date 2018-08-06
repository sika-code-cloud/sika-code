package com.easy.cloud.core.search.entry;

import com.easy.cloud.core.search.annotation.Document;
import com.easy.cloud.core.search.annotation.Mapping;
import com.easy.cloud.core.search.annotation.Setting;

/**
 * @Title: MyLog2
 * @Description:
 * @Author tudou
 * @Date 2018/8/3 16:26
 */
@Document(indexName = "mylog",type = "log")
@Setting(settingPath = "/setting/mylog_setting.json")
@Mapping(mappingPath = "/mapping/mylog_log2_mapping.json")
public class MyLogTemplate {
}
