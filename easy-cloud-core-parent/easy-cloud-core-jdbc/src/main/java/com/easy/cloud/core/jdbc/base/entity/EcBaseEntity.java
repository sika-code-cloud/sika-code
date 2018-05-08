package com.easy.cloud.core.jdbc.base.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.easy.cloud.core.jdbc.audit.annotation.EcGenericGenerator;
import com.easy.cloud.core.jdbc.base.primarykey.snowflake.EcPrimaryKeySnowflakeGenerator;

/**
 * 
 * @ClassName : DqBaseDO 
 * @Description : 所有持久化实体的基础类 --阿里规范手册建议实用DO为后缀--但是若包已do后缀结尾将报错--因此使用entity作为后缀
 * @author daiqi
 * @date 2017年12月4日 下午12:47:39 
 *
 */
@MappedSuperclass
public class EcBaseEntity {
	@Id
    @EcGenericGenerator(primaryKeyGeneratorClass = EcPrimaryKeySnowflakeGenerator.class)
	private Long id;
	@CreatedDate
	private Date createDate;
	@LastModifiedDate
	private Date updateDate;
	@Version
	private Integer version;
	@CreatedBy
	private Long createBy;
	@LastModifiedBy
	private Long updateBy;
	/** 删除标志  */
	private Integer isDeleted;
	/** 备注 */
	private String remark;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
