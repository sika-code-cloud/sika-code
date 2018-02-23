package com.dq.easy.cloud.pay.wx.pojo.bo;

import com.alibaba.fastjson.JSONObject;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * <p>
 * 微信交易类型
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午4:02:36
 */
public enum  DqWxTransactionType implements DqTransactionType {
    /**
     * 公众号支付
     */
    JSAPI("pay/unifiedorder") {
        @Override
        public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
            parameters.put("openid", order.getOpenid());
        }
    },
    /**
     * 扫码付
     */
    NATIVE("pay/unifiedorder") {
        @Override
        public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
            parameters.put("product_id", order.getOutTradeNo());
        }
    },
    /**
     * 移动支付
     */
    APP("pay/unifiedorder"),
    /**
     * H5支付
     */
    MWEB("pay/unifiedorder"){
        @Override
        public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
            //H5支付专用
            LinkedHashMap value = new LinkedHashMap();
            value.put("type", "Wap");
            //WAP网站URL地址
            value.put("wap_url", order.getWapUrl());
            //WAP 网站名
            value.put("wap_name", order.getWapName());
            JSONObject sceneInfo = new JSONObject();
            sceneInfo.put("h5_info", value);
            parameters.put("scene_info", sceneInfo.toJSONString());
        }
    },
    /**
     * 刷卡付
     */
    MICROPAY("pay/micropay"){
        @Override
        public void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order) {
            parameters.put("auth_code", order.getAuthCode());
            parameters.remove("notify_url");
            parameters.remove("trade_type");
        }
    },
    // TODO 2017/3/8 19:14 author: egan  交易辅助接口
    /**
     * 查询订单
     */
    QUERY("pay/orderquery"),
    /**
     * 关闭订单
     */
    CLOSE("pay/closeorder"),
    /**
     * 申请退款
     */
    REFUND("secapi/pay/refund"),
    /**
     * 查询退款
     */
    REFUNDQUERY("pay/refundquery"),
    /**
     * 下载对账单
     */
    DOWNLOADBILL("pay/downloadbill"),
    /**
     * 银行卡转账
     */
    BANK("mmpaysptrans/pay_bank"),
    /**
     *  转账查询
     */
    QUERY_BANK("mmpaysptrans/query_bank")
    ;

    DqWxTransactionType(String method) {
        this.method = method;
    }

    private String method;

    @Override
    public String getType() {
        return this.name();
    }
    @Override
    public String getMethod() {
        return this.method;
    }
    public  void setAttribute(Map<String, Object> parameters, DqPayOrderDTO order){

    }
}
