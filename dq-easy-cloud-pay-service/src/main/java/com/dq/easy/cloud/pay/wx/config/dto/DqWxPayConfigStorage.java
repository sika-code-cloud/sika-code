package com.dq.easy.cloud.pay.wx.config.dto;

import com.dq.easy.cloud.pay.model.payment.config.DqPayConfigStorageAbstract;

/**
 * 
 * <p>
 * 微信配置存储数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:53:19
 */
public class DqWxPayConfigStorage extends DqPayConfigStorageAbstract {

	/**
	 * 应用id
	 */
	private String appid;
	/**
	 * 商户号 合作者id
	 */
	private String mchId;

	public static DqWxPayConfigStorage newInstance(){
		return new DqWxPayConfigStorage();
	}
	
	@Override
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * 合作商唯一标识
	 */
	@Override
	public String getPid() {
		return mchId;
	}

	@Override
	public String getSeller() {
		return null;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	/**
	 * 为商户平台设置的密钥key
	 * 
	 * @return 微信密钥
	 */
	public String getSecretKey() {
		return getKeyPrivate();
	}

	public void setSecretKey(String secretKey) {
		setKeyPrivate(secretKey);
	}
}
