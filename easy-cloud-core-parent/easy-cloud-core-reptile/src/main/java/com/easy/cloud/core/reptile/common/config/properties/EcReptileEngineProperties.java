package com.easy.cloud.core.reptile.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 爬虫引擎配置类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月6日 下午2:49:47
 */
@Component
@ConfigurationProperties(prefix="ec.reptile.engine")
public class EcReptileEngineProperties {
	private String classpath;
	private Integer interval;
	private Boolean loop;
	private Integer thread;
	private Boolean debug;
	private Boolean mobile;
	private Integer retry;

	public String getClasspath() {
		if (EcStringUtils.isEmpty(this.classpath)) {
			this.classpath = "com.easy.cloud.core.reptile";
		}
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	public Integer getInterval() {
		if (EcBaseUtils.isNull(this.interval)) {
			this.interval = 1000;
		}
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Boolean getLoop() {
		if (EcBaseUtils.isNull(this.loop)) {
			this.loop = false;
		}
		return loop;
	}

	public void setLoop(Boolean loop) {
		this.loop = loop;
	}

	public Integer getThread() {
		if (EcBaseUtils.isNull(this.thread)) {
			this.thread = 1;
		}
		return thread;
	}

	public void setThread(Integer thread) {
		this.thread = thread;
	}

	public Boolean getDebug() {
		if (EcBaseUtils.isNull(this.debug)) {
			this.debug = false;
		}
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public Boolean getMobile() {
		if (EcBaseUtils.isNull(this.mobile)) {
			this.mobile = false;
		}
		return mobile;
	}

	public void setMobile(Boolean mobile) {
		this.mobile = mobile;
	}

	public Integer getRetry() {
		if (EcBaseUtils.isNull(this.retry)) {
			this.retry = 3;
		}
		return retry;
	}

	public void setRetry(Integer retry) {
		this.retry = retry;
	}

}
