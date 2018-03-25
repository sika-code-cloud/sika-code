package com.dq.easy.user.entity;

import javax.persistence.Column;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.annotation.JSONField;
import java.lang.String;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import java.io.Serializable;
import javax.imageio.stream.ImageInputStream;

/**
 * 描述：测试 
 */
@Service(value = "userService")
public class UserDO extends DqBaseBO implements Serializable,ImageInputStream {
	/** 用户名称 */
	@Column(name = "name", columnDefinition = "VARCHAR")
	@JSONField(deserialize = true, name = "name")
	private String name;
	
	/** 获取名称 */
	public String getName() {
		return this.name;
	}

	/** 获取名称 */
	public void setName(String name) {
		this.name = name;
	}

}
