package com.easy.cloud.core.reptile.common.pipeline.rest;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.reptile.common.pipeline.EcBaseJsonPipeline;
import com.geccocrawler.gecco.annotation.PipelineName;

@PipelineName(value = "redisJsonPipeline")
public class EcRestJsonPipeline extends EcBaseJsonPipeline{

	@Override
	public void doProcess(JSONObject jo) {
		logger.info(Thread.currentThread().getName() + "---rest获取到的数据：" + JSONObject.toJSONString(jo));
	}

}
