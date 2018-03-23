package com.dq.easy.cloud.pay.module.base.constant;

/**
 * 
 * <p>
 * 货币类型
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:10:19
 */
public enum DqCurType {

    CNY("人民币"),
    USD("美元"),
    HKD("港币"),
    MOP("澳门元"),
    EUR("欧元"),
    TWD("新台币"),
    KRW("韩元"),
    JPY("日元"),
    SGD("新加坡元"),
    AUD("澳大利亚元");
    /**
     * 币种名称
     */
    private String name;
    //索引
    private int index;

    /**
     * 构造函数
     * @param name
     */
    DqCurType(String name) {
        this.name = name;
    }

	public String getName() {
		return name;
	}


	public int getIndex() {
		return index;
	}

}
