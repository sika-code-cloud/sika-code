package com.sika.code.mq.common.generator;

import com.sika.code.basic.errorcode.BaseErrorCode;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.mq.common.dto.MqMsgDTO;
import com.sika.code.mq.common.constant.MqDataType;
import com.sika.code.no.generator.NoGenerator;
import com.sika.code.no.properties.NoProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息队列创建者
 *
 * @author daiqi
 * @create 2019-06-29 20:50
 */
@Data
@Accessors(chain = true)
public class MqMsgGenerator {
    /**
     * result的msgNo默认前缀
     */
    private static final String PREFIX_DEFAULT = "MQN";
    /**
     * result的msgNo默认后缀长度
     */
    private static final int SUFFIX_COUNT_DEFAULT = 6;

    private NoProperties noProperties = new NoProperties(PREFIX_DEFAULT, SUFFIX_COUNT_DEFAULT);

    /**
     * 编号生成器
     */
    private NoGenerator noGenerator;

    /**
     * <p>
     * 创建MqMsg数据传输对象
     * </p>
     *
     * @param data : 传输的数据
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/7/5 21:20
     */
    public MqMsgDTO generateMqMsg(Object data) {
        return generateMqMsg(data, noProperties.getPrefix());
    }

    /**
     * <p>
     * 创建MqMsg数据传输对象
     * </p>
     *
     * @param data : 传输的数据
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/7/5 21:20
     */
    public MqMsgDTO generateMqMsg(Object data, BaseErrorCode errorCode) {
        return generateMqMsg(data, errorCode, noProperties.getPrefix());
    }

    /**
     * <p>
     * 创建MqMsg数据传输对象
     * </p>
     *
     * @param data : 传输的数据
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/7/5 21:20
     */
    public MqMsgDTO generateMqMsg(Object data, BaseErrorCode errorCode, String prefix) {
        MqMsgDTO mqMsgDTO = MqMsgDTO.newInstance(data, errorCode);
        buildMsgNo(mqMsgDTO, prefix);
        return mqMsgDTO;
    }

    /**
     * <p>
     * 创建MqMsg数据传输对象
     * </p>
     *
     * @param data   : 传输的数据
     * @param prefix ：消息编号前缀
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/7/5 21:19
     */
    public MqMsgDTO generateMqMsg(Object data, String prefix) {
        MqMsgDTO mqMsgDTO = MqMsgDTO.newInstance(data);
        buildMsgNo(mqMsgDTO, prefix);
        return mqMsgDTO;
    }

    /**
     * <p>
     * 创建MqMsg数据传输对象
     * </p>
     *
     * @param data     : 传输的数据
     * @param dataType : 传输的数据类型
     * @param prefix   ：消息编号前缀
     * @return MqMsgDTO
     * @author daiqi
     * @date 2019/7/5 21:19
     */
    public MqMsgDTO generateMqMsg(Object data, MqDataType dataType, String prefix) {
        MqMsgDTO mqMsgDTO = MqMsgDTO.newInstance(data);
        buildMsgNo(mqMsgDTO, prefix);
        mqMsgDTO.setDataType(dataType);
        return mqMsgDTO;
    }

    private void buildMsgNo(MqMsgDTO mqMsgDTO, String prefix) {
        if (BaseUtil.isNotNull(noGenerator)) {
            mqMsgDTO.setMsgNo(noGenerator.generateNoCore(prefix, noProperties.getSuffixCount()));
        }
    }


}
