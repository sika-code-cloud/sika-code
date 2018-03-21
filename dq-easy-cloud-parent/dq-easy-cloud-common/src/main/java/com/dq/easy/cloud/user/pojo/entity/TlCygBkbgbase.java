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
    @Column(name = "create_by",columnDefinition = "BIGINT")
    private BIGINT createBy;
    /** 更新者，若无特殊要求其值由框架管理 */
    @Column(name = "update_by",columnDefinition = "BIGINT")
    private BIGINT updateBy;
    /** 数据更新时间,若无特殊要求，由框架自动生成 */
    @Column(name = "update_date",columnDefinition = "TIMESTAMP")
    private TIMESTAMP updateDate;
    /** 数据创建时间，若无特殊要求，由框架自动生成 */
    @Column(name = "create_date",columnDefinition = "TIMESTAMP")
    private TIMESTAMP createDate;
    /** 删除标志 0:未删 1：已删 */
    @Column(name = "is_deleted",columnDefinition = "TINYINT")
    private TINYINT isDeleted;
    /** 可用状态 0:不可用 1：可用 */
    @Column(name = "available",columnDefinition = "TINYINT")
    private TINYINT available;
    /** 角色售卖订单号 */
    @Column(name = "role_serial_num",columnDefinition = "BIGINT")
    private BIGINT roleSerialNum;
    /** 仓库金币 单位为铜币 */
    @Column(name = "bank_gold",columnDefinition = "INT")
    private INT bankGold;
    /** 金币 单位为铜币 */
    @Column(name = "gold",columnDefinition = "INT")
    private INT gold;
    /** 交子 单位为铜币 */
    @Column(name = "jiao_zi",columnDefinition = "INT")
    private INT jiaoZi;
    /** 材料栏数量 */
    @Column(name = "cailiao_num",columnDefinition = "INT")
    private INT cailiaoNum;
    /** 仓库格子数量 */
    @Column(name = "cangku_num",columnDefinition = "INT")
    private INT cangkuNum;
    /** 道具栏数量 */
    @Column(name = "daoju_num",columnDefinition = "INT")
    private INT daojuNum;
    /** 绑定元宝 */
    @Column(name = "bind_yuan_bao",columnDefinition = "INT")
    private INT bindYuanBao;
    /** 通宝 */
    @Column(name = "tong_bao",columnDefinition = "INT")
    private INT tongBao;
    /** 元宝 */
    @Column(name = "yuan_bao",columnDefinition = "INT")
    private INT yuanBao;

    public BIGINT getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(BIGINT createBy) {
        this.createBy = createBy;
    }
    
    public BIGINT getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(BIGINT updateBy) {
        this.updateBy = updateBy;
    }
    
    public TIMESTAMP getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(TIMESTAMP updateDate) {
        this.updateDate = updateDate;
    }
    
    public TIMESTAMP getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(TIMESTAMP createDate) {
        this.createDate = createDate;
    }
    
    public TINYINT getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(TINYINT isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public TINYINT getAvailable() {
        return this.available;
    }

    public void setAvailable(TINYINT available) {
        this.available = available;
    }
    
    public BIGINT getRoleSerialNum() {
        return this.roleSerialNum;
    }

    public void setRoleSerialNum(BIGINT roleSerialNum) {
        this.roleSerialNum = roleSerialNum;
    }
    
    public INT getBankGold() {
        return this.bankGold;
    }

    public void setBankGold(INT bankGold) {
        this.bankGold = bankGold;
    }
    
    public INT getGold() {
        return this.gold;
    }

    public void setGold(INT gold) {
        this.gold = gold;
    }
    
    public INT getJiaoZi() {
        return this.jiaoZi;
    }

    public void setJiaoZi(INT jiaoZi) {
        this.jiaoZi = jiaoZi;
    }
    
    public INT getCailiaoNum() {
        return this.cailiaoNum;
    }

    public void setCailiaoNum(INT cailiaoNum) {
        this.cailiaoNum = cailiaoNum;
    }
    
    public INT getCangkuNum() {
        return this.cangkuNum;
    }

    public void setCangkuNum(INT cangkuNum) {
        this.cangkuNum = cangkuNum;
    }
    
    public INT getDaojuNum() {
        return this.daojuNum;
    }

    public void setDaojuNum(INT daojuNum) {
        this.daojuNum = daojuNum;
    }
    
    public INT getBindYuanBao() {
        return this.bindYuanBao;
    }

    public void setBindYuanBao(INT bindYuanBao) {
        this.bindYuanBao = bindYuanBao;
    }
    
    public INT getTongBao() {
        return this.tongBao;
    }

    public void setTongBao(INT tongBao) {
        this.tongBao = tongBao;
    }
    
    public INT getYuanBao() {
        return this.yuanBao;
    }

    public void setYuanBao(INT yuanBao) {
        this.yuanBao = yuanBao;
    }
    

}