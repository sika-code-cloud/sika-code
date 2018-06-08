package com.easy.cloud.core.reptile.common.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.pipeline.JsonPipeline;


public abstract class EcBaseJsonPipeline extends JsonPipeline {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public final void process(JSONObject jo) {
		try {
			doProcess(jo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public abstract void doProcess(JSONObject jsonObject);
}
