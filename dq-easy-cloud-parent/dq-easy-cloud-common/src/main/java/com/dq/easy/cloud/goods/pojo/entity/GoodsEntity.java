package com.dq.easy.cloud.goods.pojo.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;

/**
 * 描述：商品模型
 * @author daiqi
 * @date 2018-03-23 22:36:56
 */
@Entity
@Table(name = "tl_cyg_goods_info")
public class GoodsEntity extends DqBaseEntity {
    /** 角色售卖订单号 */
    @Column(name = "role_serial_num", columnDefinition = "BIGINT")
    private Long roleSerialNum;
    /** 实际的Icon路径 */
    @Column(name = "actual_icon", columnDefinition = "VARCHAR")
    private String actualIcon;
    /** 数据编号 */
    @Column(name = "data_id", columnDefinition = "BIGINT")
    private Long dataId;
    /** ext_type */
    @Column(name = "ext_type", columnDefinition = "INT")
    private Integer extType;
    /** 贵重标志0 不是贵重 1 贵重 */
    @Column(name = "gui_zhong", columnDefinition = "INT")
    private Integer guiZhong;
    /** 图片位置 此为相对位置 */
    @Column(name = "icon", columnDefinition = "VARCHAR")
    private String icon;
    /** 是否绑定 0 否 1 是 */
    @Column(name = "is_bind", columnDefinition = "INT")
    private Integer isBind;
    /** is_gem */
    @Column(name = "is_gem", columnDefinition = "INT")
    private Integer isGem;
    /** 名称 */
    @Column(name = "name", columnDefinition = "VARCHAR")
    private String name;
    /** 数量 */
    @Column(name = "num", columnDefinition = "INT")
    private Integer num;
    /** position */
    @Column(name = "pos", columnDefinition = "INT")
    private Integer pos;
    /** 类型描述 */
    @Column(name = "type_desc", columnDefinition = "VARCHAR")
    private String typeDesc;
    /** use_time_desc */
    @Column(name = "use_time_desc", columnDefinition = "VARCHAR")
    private String useTimeDesc;
    /** use_time_desc2 */
    @Column(name = "use_time_desc2", columnDefinition = "VARCHAR")
    private String useTimeDesc2;

    public Long getRoleSerialNum() {
        return this.roleSerialNum;
    }

    public void setRoleSerialNum(Long roleSerialNum) {
        this.roleSerialNum = roleSerialNum;
    }
    
    public String getActualIcon() {
        return this.actualIcon;
    }

    public void setActualIcon(String actualIcon) {
        this.actualIcon = actualIcon;
    }
    
    public Long getDataId() {
        return this.dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }
    
    public Integer getExtType() {
        return this.extType;
    }

    public void setExtType(Integer extType) {
        this.extType = extType;
    }
    
    public Integer getGuiZhong() {
        return this.guiZhong;
    }

    public void setGuiZhong(Integer guiZhong) {
        this.guiZhong = guiZhong;
    }
    
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public Integer getIsBind() {
        return this.isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }
    
    public Integer getIsGem() {
        return this.isGem;
    }

    public void setIsGem(Integer isGem) {
        this.isGem = isGem;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    
    public Integer getPos() {
        return this.pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
    
    public String getTypeDesc() {
        return this.typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }
    
    public String getUseTimeDesc() {
        return this.useTimeDesc;
    }

    public void setUseTimeDesc(String useTimeDesc) {
        this.useTimeDesc = useTimeDesc;
    }
    
    public String getUseTimeDesc2() {
        return this.useTimeDesc2;
    }

    public void setUseTimeDesc2(String useTimeDesc2) {
        this.useTimeDesc2 = useTimeDesc2;
    }
    

}
