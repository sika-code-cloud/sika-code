package com.easy.cloud.movie.base.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easy.cloud.core.basic.pojo.entity.EcBaseEntity;

/**
 * 电影实体类
 * @author daiqi
 * @date 2018年1月8日 上午12:05:14
 */
@Entity
@Table(name = "easy_movie_info")
public class MovieEntity extends EcBaseEntity {
	/** VARCHAR(31) NOT NULL DEFAULT '' COMMENT '电影名称'*/
	private String movieName; 
	/** VARCHAR(125) NOT NULL DEFAULT '' COMMENT '电影描述'*/
	private String movieDesc; // 
	/** INT(11) NOT NULL DEFAULT 0 COMMENT '播放时长 精确到秒'*/
	private String playDuration;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public String getPlayDuration() {
		return playDuration;
	}

	public void setPlayDuration(String playDuration) {
		this.playDuration = playDuration;
	}

}
