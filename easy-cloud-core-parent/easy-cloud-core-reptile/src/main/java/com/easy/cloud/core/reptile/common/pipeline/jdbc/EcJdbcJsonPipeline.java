package com.easy.cloud.core.reptile.common.pipeline.jdbc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.reptile.common.pipeline.EcBaseJsonPipeline;
import com.easy.cloud.core.reptile.common.selector.EcReptileSelector;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.scheduler.SchedulerContext;

@PipelineName(value = "jdbcJsonPipeline")
public class EcJdbcJsonPipeline extends EcBaseJsonPipeline {

	@Override
	public void doProcess(JSONObject jo) {
//		EcReptileSelector.getReptileDynamicBeanService().reptileData(null);
		System.out.println(Thread.currentThread().getName() + "---jdbc获取到的数据：" + JSONObject.toJSONString(jo));
		JSONArray jsonArray = new JSONArray(jo.getJSONArray("list"));
		HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
		String url = jo.getString("url");
		if (url != null) {
			HttpRequest sub = currRequest.subRequest(url);
			DeriveSchedulerContext.into(sub);
		}
	}

}
