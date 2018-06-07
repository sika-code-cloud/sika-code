package com.easy.cloud.core.reptile.common.pipeline;

import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileKeyValueDTO;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;

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
		System.out.println(Thread.currentThread().getName() + "获取到的数据：" + JSONObject.toJSONString(jo));
//		try {
//			EcReptileKeyValueDTO [] reptileKeyValueDTOs = queue.take();
//			String tempUrl = EcReptileConstant.MATCH_URL_DETAIL;
//			for (EcReptileKeyValueDTO reptileKeyValueDTO : reptileKeyValueDTOs) {
//				tempUrl = tempUrl.replace(reptileKeyValueDTO.getKey(), reptileKeyValueDTO.getValue());
//			}
//			String fullUrl = tempUrl.replace("{", "").replace("}", "");
//			DeriveSchedulerContext.into(new HttpGetRequest(fullUrl));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
