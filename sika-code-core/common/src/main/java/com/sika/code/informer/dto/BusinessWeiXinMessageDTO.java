package com.sika.code.informer.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sika.code.informer.constant.MsgTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author daiqi
 * @create 2020-05-15 15:10
 */
public class BusinessWeiXinMessageDTO implements BaseThirdMessageDTO {

    public static final String AIT_ALL = "@all";

    public static final String MSG_TYPE = "msgtype";
    public static final String CONTENT = "content";
    /**
     * 需要aite的收款人的微信英文名 @All 表示艾特所有人
     */
    public static final String MENTIONED_LIST = "mentioned_list";
    /**
     * 需要aite的收款人的手机号 @All 表示艾特所有人
     */
    public static final String MENTIONED_MOBILE_LIST = "mentioned_mobile_list";

    private MsgTypeEnum msgTypeEnum;
    private WeiXinMsgDTO weiXinMsgDTO;

    private Map<String, Object> sendMsg;

    public BusinessWeiXinMessageDTO() {
        msgTypeEnum = MsgTypeEnum.TEXT;
        weiXinMsgDTO = new WeiXinMsgDTO();
        sendMsg = Maps.newHashMap();
    }

    public MsgTypeEnum getMsgTypeEnum() {
        return msgTypeEnum;
    }

    public BusinessWeiXinMessageDTO setMsgTypeEnum(MsgTypeEnum msgTypeEnum) {
        this.msgTypeEnum = msgTypeEnum;
        return this;
    }

    public WeiXinMsgDTO getWeiXinMsgDTO() {
        return weiXinMsgDTO;
    }

    public BusinessWeiXinMessageDTO setWeiXinMsgDTO(WeiXinMsgDTO weiXinMsgDTO) {
        this.weiXinMsgDTO = weiXinMsgDTO;
        return this;
    }

    @Override
    public Map<String, Object> getSendMsg() {
        return sendMsg;
    }

    public BusinessWeiXinMessageDTO buildAitAll() {
        if (this.weiXinMsgDTO == null) {
            return null;
        }
        if (this.weiXinMsgDTO.getMentioned_list() == null) {
            this.weiXinMsgDTO.setMentioned_list(new ArrayList<>());
        }

        this.weiXinMsgDTO.appendMentioned_list(AIT_ALL);
        return this;
    }

    public BusinessWeiXinMessageDTO build() {
        if (msgTypeEnum == null) {
            msgTypeEnum = MsgTypeEnum.TEXT;
        }
        if (weiXinMsgDTO == null) {
            weiXinMsgDTO = new WeiXinMsgDTO();
        }
        if (sendMsg == null) {
            sendMsg = Maps.newHashMap();
        }
        String msgType = msgTypeEnum.getCode();
        this.sendMsg.put(MSG_TYPE, msgType);
        this.sendMsg.put(msgType, weiXinMsgDTO);
        return this;
    }

    @Data
    @Accessors(chain = true)
    public static class WeiXinMsgDTO {

        private String content;
        /**
         * 需要aite的收款人的微信英文名 @All 表示艾特所有人
         */
        private List<String> mentioned_list;
        /**
         * 需要aite的收款人的手机号 @All 表示艾特所有人
         */
        private List<String> mentioned_mobile_list;

        public WeiXinMsgDTO appendMentioned_list(String mentioned) {
            if (this.mentioned_list == null) {
                this.mentioned_list = Lists.newArrayList();
            }
            this.mentioned_list.add(mentioned);
            return this;
        }

        public WeiXinMsgDTO appendMentioned_mobile_list(String mentioned_mobile) {
            if (this.mentioned_mobile_list == null) {
                this.mentioned_mobile_list = Lists.newArrayList();
            }
            this.mentioned_mobile_list.add(mentioned_mobile);
            return this;
        }
    }
}
