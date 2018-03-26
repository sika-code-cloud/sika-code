package com.dq.easy.goods.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import javax.persistence.Entity;
/**
 * @author 
 * @date 2018-03-26 16:12:11
 * 描述：商品
 */
@Table(name = "tl_cyg_goods_info")
@Entity
public class GoodsDO extends DqBaseBO{
	/** 角色售卖订单号 */
	@Column(name = "role_serial_num")
	public Long roleSerialNum;
	/** 实际的Icon路径 */
	@Column(name = "actual_icon")
	public String actualIcon;
	/** 数据编号 */
	@Column(name = "data_id")
	public Long dataId;
	/** ext_type */
	@Column(name = "ext_type")
	public Integer extType;
	/** 贵重标志0 不是贵重 1 贵重 */
	@Column(name = "gui_zhong")
	public Integer guiZhong;
	/** 图片位置 此为相对位置 */
	@Column(name = "icon")
	public String icon;
	/** 是否绑定 0 否 1 是 */
	@Column(name = "is_bind")
	public Integer isBind;
	/** is_gem */
	@Column(name = "is_gem")
	public Integer isGem;
	/** 名称 */
	@Column(name = "name")
	public String name;
	/** 数量 */
	@Column(name = "num")
	public Integer num;
	/** position */
	@Column(name = "pos")
	public Integer pos;
	/** 类型描述 */
	@Column(name = "type_desc")
	public String typeDesc;
	/** use_time_desc */
	@Column(name = "use_time_desc")
	public String useTimeDesc;
	/** use_time_desc2 */
	@Column(name = "use_time_desc2")
	public String useTimeDesc2;
	
	/** 获取角色售卖订单号 */
	public Long getRoleSerialNum() {
		return this.roleSerialNum;
	}
		
	/** 设置角色售卖订单号 */
	public void setRoleSerialNum(Long roleSerialNum) {
		this.roleSerialNum = roleSerialNum;
	}
		
	/** 构建角色售卖订单号 */
	public GoodsDO buildRoleSerialNum(Long roleSerialNum) {
		this.roleSerialNum = roleSerialNum;
		return this;
	}
		
	/** 获取实际的Icon路径 */
	public String getActualIcon() {
		return this.actualIcon;
	}
		
	/** 设置实际的Icon路径 */
	public void setActualIcon(String actualIcon) {
		this.actualIcon = actualIcon;
	}
		
	/** 构建实际的Icon路径 */
	public GoodsDO buildActualIcon(String actualIcon) {
		this.actualIcon = actualIcon;
		return this;
	}
		
	/** 获取数据编号 */
	public Long getDataId() {
		return this.dataId;
	}
		
	/** 设置数据编号 */
	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
		
	/** 构建数据编号 */
	public GoodsDO buildDataId(Long dataId) {
		this.dataId = dataId;
		return this;
	}
		
	/** 获取ext_type */
	public Integer getExtType() {
		return this.extType;
	}
		
	/** 设置ext_type */
	public void setExtType(Integer extType) {
		this.extType = extType;
	}
		
	/** 构建ext_type */
	public GoodsDO buildExtType(Integer extType) {
		this.extType = extType;
		return this;
	}
		
	/** 获取贵重标志0 不是贵重 1 贵重 */
	public Integer getGuiZhong() {
		return this.guiZhong;
	}
		
	/** 设置贵重标志0 不是贵重 1 贵重 */
	public void setGuiZhong(Integer guiZhong) {
		this.guiZhong = guiZhong;
	}
		
	/** 构建贵重标志0 不是贵重 1 贵重 */
	public GoodsDO buildGuiZhong(Integer guiZhong) {
		this.guiZhong = guiZhong;
		return this;
	}
		
	/** 获取图片位置 此为相对位置 */
	public String getIcon() {
		return this.icon;
	}
		
	/** 设置图片位置 此为相对位置 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
		
	/** 构建图片位置 此为相对位置 */
	public GoodsDO buildIcon(String icon) {
		this.icon = icon;
		return this;
	}
		
	/** 获取是否绑定 0 否 1 是 */
	public Integer getIsBind() {
		return this.isBind;
	}
		
	/** 设置是否绑定 0 否 1 是 */
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}
		
	/** 构建是否绑定 0 否 1 是 */
	public GoodsDO buildIsBind(Integer isBind) {
		this.isBind = isBind;
		return this;
	}
		
	/** 获取is_gem */
	public Integer getIsGem() {
		return this.isGem;
	}
		
	/** 设置is_gem */
	public void setIsGem(Integer isGem) {
		this.isGem = isGem;
	}
		
	/** 构建is_gem */
	public GoodsDO buildIsGem(Integer isGem) {
		this.isGem = isGem;
		return this;
	}
		
	/** 获取名称 */
	public String getName() {
		return this.name;
	}
		
	/** 设置名称 */
	public void setName(String name) {
		this.name = name;
	}
		
	/** 构建名称 */
	public GoodsDO buildName(String name) {
		this.name = name;
		return this;
	}
		
	/** 获取数量 */
	public Integer getNum() {
		return this.num;
	}
		
	/** 设置数量 */
	public void setNum(Integer num) {
		this.num = num;
	}
		
	/** 构建数量 */
	public GoodsDO buildNum(Integer num) {
		this.num = num;
		return this;
	}
		
	/** 获取position */
	public Integer getPos() {
		return this.pos;
	}
		
	/** 设置position */
	public void setPos(Integer pos) {
		this.pos = pos;
	}
		
	/** 构建position */
	public GoodsDO buildPos(Integer pos) {
		this.pos = pos;
		return this;
	}
		
	/** 获取类型描述 */
	public String getTypeDesc() {
		return this.typeDesc;
	}
		
	/** 设置类型描述 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
		
	/** 构建类型描述 */
	public GoodsDO buildTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
		return this;
	}
		
	/** 获取use_time_desc */
	public String getUseTimeDesc() {
		return this.useTimeDesc;
	}
		
	/** 设置use_time_desc */
	public void setUseTimeDesc(String useTimeDesc) {
		this.useTimeDesc = useTimeDesc;
	}
		
	/** 构建use_time_desc */
	public GoodsDO buildUseTimeDesc(String useTimeDesc) {
		this.useTimeDesc = useTimeDesc;
		return this;
	}
		
	/** 获取use_time_desc2 */
	public String getUseTimeDesc2() {
		return this.useTimeDesc2;
	}
		
	/** 设置use_time_desc2 */
	public void setUseTimeDesc2(String useTimeDesc2) {
		this.useTimeDesc2 = useTimeDesc2;
	}
		
	/** 构建use_time_desc2 */
	public GoodsDO buildUseTimeDesc2(String useTimeDesc2) {
		this.useTimeDesc2 = useTimeDesc2;
		return this;
	}
		
}
