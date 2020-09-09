package com.zyd.blog.business.vo;

import com.zyd.blog.framework.object.BaseConditionVO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:40
 * @since 1.0
 */
@Getter
@Setter
public class LogConditionVO extends BaseConditionVO {
	private Long userId;
	private String logLevel;
	private String ip;
	private String content;
	private String params;
	private String type;
	private String ua;
	private String os;
	private String browser;
	private String spiderType;
	private String requestUrl;
	private String referer;
	private Boolean spider;
}

