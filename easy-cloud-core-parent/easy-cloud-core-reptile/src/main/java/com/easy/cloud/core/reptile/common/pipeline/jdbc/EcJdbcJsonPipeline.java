package com.easy.cloud.core.reptile.common.pipeline.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.reptile.common.pipeline.EcBaseJsonPipeline;
import com.geccocrawler.gecco.annotation.PipelineName;

@PipelineName(value = "jdbcJsonPipeline")
public class EcJdbcJsonPipeline extends EcBaseJsonPipeline{

	@Override
	public void process(JSONObject jo) {
		System.out.println(Thread.currentThread().getName() + "---jdbc获取到的数据：" + JSONObject.toJSONString(jo));
		System.out.println(jo.getJSONArray("roleDetail").size());
	}

}
