package com.easy.cloud.core.reptile.common.pipeline.mq;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.reptile.common.pipeline.EcBaseJsonPipeline;
import com.geccocrawler.gecco.annotation.PipelineName;

@PipelineName(value = "mqJsonPipeline")
public class EcMqJsonPipeline extends EcBaseJsonPipeline{

	@Override
	public void doProcess(JSONObject jo) {
		System.out.println(Thread.currentThread().getName() + "---mq获取到的数据：" + JSONObject.toJSONString(jo));
	}
}
