package com.dq.easy.cloud.user.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
 * 描述：质量问题模型
 * @author THINK
 * @date 2017/05/03
 */
@Entity
@Table(name="tl_cyg_bkbgbase_info")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class TlCygBkbgbase extends BaseModel implements ICreateListenable,IModifyListenable,IDeleteListenable {

    /** 创建者，若无特殊要求其值由框架管理 */
    @Column(name = "create_by",columnDefinition = "Long")
    private Long createBy;
    /** 更新者，若无特殊要求其值由框架管理 */
    @Column(name = "update_by",columnDefinition = "Long")
    private Long updateBy;
    /** 数据更新时间,若无特殊要求，由框架自动生成 */
    @Column(name = "update_date",columnDefinition = "Date")
    private Date updateDate;
    /** 数据创建时间，若无特殊要求，由框架自动生成 */
    @Column(name = "create_date",columnDefinition = "Date")
    private Date createDate;
    /** 删除标志 0:未删 1：已删 */
    @Column(name = "is_deleted",columnDefinition = "Integer")
    private Integer isDeleted;
    /** 可用状态 0:不可用 1：可用 */
    @Column(name = "available",columnDefinition = "Integer")
    private Integer available;
    /** 角色售卖订单号 */
    @Column(name = "role_serial_num",columnDefinition = "Long")
    private Long roleSerialNum;
    /** 仓库金币 单位为铜币 */
    @Column(name = "bank_gold",columnDefinition = "Integer")
    private Integer bankGold;
    /** 金币 单位为铜币 */
    @Column(name = "gold",columnDefinition = "Integer")
    private Integer gold;
    /** 交子 单位为铜币 */
    @Column(name = "jiao_zi",columnDefinition = "Integer")
    private Integer jiaoZi;
    /** 材料栏数量 */
    @Column(name = "cailiao_num",columnDefinition = "Integer")
    private Integer cailiaoNum;
    /** 仓库格子数量 */
    @Column(name = "cangku_num",columnDefinition = "Integer")
    private Integer cangkuNum;
    /** 道具栏数量 */
    @Column(name = "daoju_num",columnDefinition = "Integer")
    private Integer daojuNum;
    /** 绑定元宝 */
    @Column(name = "bind_yuan_bao",columnDefinition = "Integer")
    private Integer bindYuanBao;
    /** 通宝 */
    @Column(name = "tong_bao",columnDefinition = "Integer")
    private Integer tongBao;
    /** 元宝 */
    @Column(name = "yuan_bao",columnDefinition = "Integer")
    private Integer yuanBao;

    public Long getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    
    public Long getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Integer getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public Integer getAvailable() {
        return this.available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
    
    public Long getRoleSerialNum() {
        return this.roleSerialNum;
    }

    public void setRoleSerialNum(Long roleSerialNum) {
        this.roleSerialNum = roleSerialNum;
    }
    
    public Integer getBankGold() {
        return this.bankGold;
    }

    public void setBankGold(Integer bankGold) {
        this.bankGold = bankGold;
    }
    
    public Integer getGold() {
        return this.gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }
    
    public Integer getJiaoZi() {
        return this.jiaoZi;
    }

    public void setJiaoZi(Integer jiaoZi) {
        this.jiaoZi = jiaoZi;
    }
    
    public Integer getCailiaoNum() {
        return this.cailiaoNum;
    }

    public void setCailiaoNum(Integer cailiaoNum) {
        this.cailiaoNum = cailiaoNum;
    }
    
    public Integer getCangkuNum() {
        return this.cangkuNum;
    }

    public void setCangkuNum(Integer cangkuNum) {
        this.cangkuNum = cangkuNum;
    }
    
    public Integer getDaojuNum() {
        return this.daojuNum;
    }

    public void setDaojuNum(Integer daojuNum) {
        this.daojuNum = daojuNum;
    }
    
    public Integer getBindYuanBao() {
        return this.bindYuanBao;
    }

    public void setBindYuanBao(Integer bindYuanBao) {
        this.bindYuanBao = bindYuanBao;
    }
    
    public Integer getTongBao() {
        return this.tongBao;
    }

    public void setTongBao(Integer tongBao) {
        this.tongBao = tongBao;
    }
    
    public Integer getYuanBao() {
        return this.yuanBao;
    }

    public void setYuanBao(Integer yuanBao) {
        this.yuanBao = yuanBao;
    }
    

}