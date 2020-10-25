package com.sika.informer;

import com.sika.code.informer.constant.MsgTypeEnum;
import com.sika.code.informer.dto.BaseThirdMessageDTO;
import com.sika.code.informer.dto.BusinessWeiXinMessageDTO;
import com.sika.code.informer.impl.InformerForBusinessWeiXin;
import org.junit.Test;

/**
 * @author daiqi
 * @create 2020-05-15 15:36
 */
public class TestWeinXinInformer {
    private static String weebHook = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=e9bff114-9578-4022-9d2a-45ce846876d8";
    private static String weebHook1 = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=e295e4cb-9b87-46a7-addd-0620f1d2755b";

    @Test
    public void testSendBusinessWeiXin() {
        String lineSplit = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<font color='info'>")
        .append("监控消息推送(2020-05-15)").append(lineSplit)
        .append("【任务类型】 发送实时还款数据").append(lineSplit)
        .append("【渠道名称】 杭银消金-乐花卡2.0").append(lineSplit)
        .append("【结算日期】 2020-05-15").append(lineSplit)
        .append("【任务状态】 异常").append(lineSplit)
        .append("【状态时间】 2020-05-15 17:02:31").append(lineSplit)
        .append("【备注】 ").append("【正式环境】【10.11.104.230】服务名称【postloan_asset_server】，环境【正式环境】，IP【10.11.104.230】，traceId【0A0A411D2E4215895333200358557】，异常类型：【】，资金方渠道【1182】，资金方名称【杭银消金-乐花卡2.】，资产号【1120051415542984294275】，期数【1】，请求编号【1120051415542984294275-1-1】，请求次数【178】，请求结果：【失败】，客户端响应：【{\"code\":\"FFFF\",\"msg\":\"还款通知接口http请求失败\",\"repayStatus\":2}】").append(lineSplit)
        .append("</font>");
        InformerForBusinessWeiXin informerForBusinessWeiXin = new InformerForBusinessWeiXin(weebHook).setAsyn(false);


        BusinessWeiXinMessageDTO.WeiXinMsgDTO weiXinMsgDTO = new BusinessWeiXinMessageDTO.WeiXinMsgDTO().setContent(stringBuilder.toString());

        BaseThirdMessageDTO baseThirdMessageDTO = new BusinessWeiXinMessageDTO()
                .setWeiXinMsgDTO(weiXinMsgDTO)
                .setMsgTypeEnum(MsgTypeEnum.MARKDOWN)
                .build()
                ;

        informerForBusinessWeiXin.inform(baseThirdMessageDTO, weebHook1);
    }
}
