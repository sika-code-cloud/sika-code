package com.easy.cloud.pay.zfb.config.dto;

import com.easy.cloud.pay.core.payment.config.dto.EcPayConfigStorageAbstract;

/**
 * 
 * <p>
 * 支付宝配置数据传输对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月27日 上午10:32:06
 */
public class EcZfbPayConfigStorageDTO extends EcPayConfigStorageAbstract{
    /**
     *  商户应用id
     */
    private volatile  String appId ;
    /**
     *  商户签约拿到的pid,partner_id的简称，合作伙伴身份等同于 partner
     */
    private volatile  String pid ;

    /**
     * 商户收款账号
     */
    private volatile  String seller;
    
	public static EcZfbPayConfigStorageDTO newInstance(){
		return new EcZfbPayConfigStorageDTO();
	}
	

	public void setAppId(String appId) {
		this.appId = appId;
	}
	public void setAppid(String appId) {
		this.appId = appId;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	@Override
	public String getAppid() {
		return appId;
	}

	@Override
	public String getPid() {
		return this.pid;
	}

	@Override
	public String getSeller() {
		return this.seller;
	}
	

}
