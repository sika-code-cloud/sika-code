package com.easy.cloud.core.reptile.pipeline;

import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;

@PipelineName(value = "ecPipelineTest")
public class EcPipelineTest extends JsonPipeline{
	private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	static {
		queue.add("http://tl.cyg.changyou.com/goods/char_detail?serial_num=20180522001288380");
		queue.add("http://tl.cyg.changyou.com/goods/char_detail?serial_num=201805271244509228");
		queue.add("http://tl.cyg.changyou.com/goods/char_detail?serial_num=20180523901412398");
		queue.add("http://tl.cyg.changyou.com/goods/char_detail?serial_num=201805242135551607");
		queue.add("http://tl.cyg.changyou.com/goods/char_detail?serial_num=201805211856244017");
	}
	@Override
	public void process(JSONObject jo) {
		System.out.println("获取到的数据：" + JSONObject.toJSONString(jo));
		try {
			DeriveSchedulerContext.into(new HttpGetRequest(queue.take()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
