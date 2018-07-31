package com.easy.cloud.core.reptile.common.pipeline.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.reptile.common.pipeline.EcBaseJsonPipeline;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;

@PipelineName(value = "jdbcJsonPipeline")
public class EcJdbcJsonPipeline extends EcBaseJsonPipeline {

	@Override
	public void doProcess(JSONObject jo) {
//		EcReptileSelector.getReptileDynamicBeanService().reptileData(null);
		logger.info(Thread.currentThread().getName() + "---jdbc获取到的数据：" + JSONObject.toJSONString(jo));
		HttpRequest currRequest = HttpGetRequest.fromJson(jo.getJSONObject("request"));
		String currentUrl = currRequest.getUrl();

		Integer currentNum = Integer.parseInt(currRequest.getParameter("pageNum"));
		if (currentUrl != null && currentNum <= 200) {
			Integer nextNum = currentNum + 1;
			String nextUrl = EcStringUtils.replace(currentUrl, "page_num="+currentNum, "page_num="+nextNum);
			DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
