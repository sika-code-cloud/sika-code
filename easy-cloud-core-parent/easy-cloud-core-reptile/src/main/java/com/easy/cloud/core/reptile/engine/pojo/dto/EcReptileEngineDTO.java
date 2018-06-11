package com.easy.cloud.core.reptile.engine.pojo.dto;

import com.easy.cloud.core.basic.pojo.dto.EcBaseDTO;

/**
 * 描述：爬虫引擎配置表数据传输类
 * 
 * @author THINK
 * @date 2018-06-11 10:59:59
 */
public class EcReptileEngineDTO extends EcBaseDTO {
	/** 爬虫引擎编号 */
	private Integer reptileEngineNo;
	/** 类扫描路径即 pipeline所在路径 */
	private String classpath;
	/** 示某个线程在抓取完成一个请求后的间隔时间，单位是毫秒，系统会在左右1秒时间内随机。如果为2000，系统会在1000～3000之间随机选取 */
	private Integer interval;
	/** 是否循环抓取 0:不循环 1:循环 默认1 */
	private Integer loop;
	/** 表示开启的爬虫线程数量，需要注意的是线程数量要小于或者等于start请求的数量 默认是1 */
	private Integer thread;
	/** 是否开启debug模式，如果开启debug模式，会在控制台输出jsoup元素抽取的日志。 0:关闭 1:开启 */
	private Integer debug;
	/** 表示使用移动端还是pc端的UserAgent。默认为1使用pc端的UserAgent 1:pc端 2:移动端  */
	private Integer mobile;
	/**  重试次数默认3  */
	private Integer retry;
	
	private String beanClassName;
		
	/** 获取爬虫引擎编号 */
	public Integer getReptileEngineNo() {
		return this.reptileEngineNo;
	}

	/** 设置爬虫引擎编号 */
	public void setReptileEngineNo(Integer reptileEngineNo) {
		this.reptileEngineNo = reptileEngineNo;
	}

	/** 获取类扫描路径即 pipeline所在路径 */
	public String getClasspath() {
		return this.classpath;
	}

	/** 设置类扫描路径即 pipeline所在路径 */
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

	/** 获取示某个线程在抓取完成一个请求后的间隔时间，单位是毫秒，系统会在左右1秒时间内随机。如果为2000，系统会在1000～3000之间随机选取 */
	public Integer getInterval() {
		return this.interval;
	}

	/** 设置示某个线程在抓取完成一个请求后的间隔时间，单位是毫秒，系统会在左右1秒时间内随机。如果为2000，系统会在1000～3000之间随机选取 */
	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	/** 获取是否循环抓取 0:不循环 1:循环 默认1 */
	public Integer getLoop() {
		return this.loop;
	}

	/** 设置是否循环抓取 0:不循环 1:循环 默认1 */
	public void setLoop(Integer loop) {
		this.loop = loop;
	}

	/** 获取表示开启的爬虫线程数量，需要注意的是线程数量要小于或者等于start请求的数量 默认是1 */
	public Integer getThread() {
		return this.thread;
	}

	/** 设置表示开启的爬虫线程数量，需要注意的是线程数量要小于或者等于start请求的数量 默认是1 */
	public void setThread(Integer thread) {
		this.thread = thread;
	}

	/** 获取是否开启debug模式，如果开启debug模式，会在控制台输出jsoup元素抽取的日志。 0:关闭 1:开启 */
	public Integer getDebug() {
		return this.debug;
	}

	/** 设置是否开启debug模式，如果开启debug模式，会在控制台输出jsoup元素抽取的日志。 0:关闭 1:开启 */
	public void setDebug(Integer debug) {
		this.debug = debug;
	}

	/** 获取表示使用移动端还是pc端的UserAgent。默认为1使用pc端的UserAgent 1:pc端 2:移动端  */
	public Integer getMobile() {
		return this.mobile;
	}

	/** 设置表示使用移动端还是pc端的UserAgent。默认为1使用pc端的UserAgent 1:pc端 2:移动端  */
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	/** 获取 重试次数默认3  */
	public Integer getRetry() {
		return this.retry;
	}

	/** 设置 重试次数默认3  */
	public void setRetry(Integer retry) {
		this.retry = retry;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}
	
}
