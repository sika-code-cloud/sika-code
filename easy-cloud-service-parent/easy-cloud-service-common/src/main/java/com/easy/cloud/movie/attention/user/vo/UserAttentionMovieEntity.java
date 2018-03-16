package com.easy.cloud.movie.attention.user.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dq.easy.cloud.model.basic.pojo.entity.DqBaseEntity;

/**
 * 用户关注电影实体
 * @author daiqi
 * 创建日期 2018年1月7日 下午11:59:16
 */
@Entity
@Table(name = "easy_user_attention_movie")
public class UserAttentionMovieEntity extends DqBaseEntity{
	/** BIGINT(20) NOT NULL COMMENT '电影id 关联easy_movie_info表的id'*/
	private Long movieId;
	/** BIGINT(20) NOT NULL COMMENT '电影id 关联easy_user_id表的id'*/
	private Long userId;
	/** VARCHAR(123) NOT NULL DEFAULT '' COMMENT '备注'*/
	private String remark;
	
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
