package com.easy.cloud.core.reptile.pipeline;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileKeyValueDTO;
import com.easy.cloud.core.reptile.constant.EcReptileConstant;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;

@PipelineName(value = "ecPipelineTest")
public class EcPipelineTest extends JsonPipeline{
	public static LinkedBlockingQueue<EcReptileKeyValueDTO []> queue = new LinkedBlockingQueue<>();
//	static {
//		try {
//			queue.put(new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", "20180522001288380")});
//			queue.put(new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", "201805271244509228")});
//			queue.put(new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", "20180523901412398")});
//			queue.put(new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", "201805242135551607")});
//			queue.put(new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", "201805211856244017")});
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	@Override
	public void process(JSONObject jo) {
		try {
			System.out.println("获取到的数据：" + JSONObject.toJSONString(jo));
			EcReptileKeyValueDTO [] reptileKeyValueDTOs = queue.take();
			String tempUrl = EcReptileConstant.MATCH_URL_DETAIL;
			for (EcReptileKeyValueDTO reptileKeyValueDTO : reptileKeyValueDTOs) {
				tempUrl = tempUrl.replace(reptileKeyValueDTO.getKey(), reptileKeyValueDTO.getValue());
			}
			String fullUrl = tempUrl.replace("{", "").replace("}", "");
			DeriveSchedulerContext.into(new HttpGetRequest(fullUrl));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
