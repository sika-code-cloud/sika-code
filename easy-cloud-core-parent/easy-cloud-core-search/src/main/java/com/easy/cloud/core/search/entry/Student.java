package com.easy.cloud.core.search.entry;

import com.easy.cloud.core.search.annotation.Document;
import com.easy.cloud.core.search.annotation.Mapping;
import com.easy.cloud.core.search.annotation.Setting;

/**
 * @Title: Student
 * @Description:
 * @Author tudou
 * @Date 2018/8/2 20:15
 */
@Document(indexName = "student",type = "student")
@Setting(settingPath = "/com/easy/cloud/core/search/entry/setting/student_setting.json")
@Mapping(mappingPath = "/com/easy/cloud/core/search/entry/mapping/student_mapping.json")
public class Student {

}
