package com.sika.code.informer;

import com.sika.code.informer.constant.MsgTypeEnum;
import com.sika.code.informer.dto.BaseThirdMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 基础异常通知者
 * </p>
 * <pre>
 * 所有异常通知需要继承该基础异常通知类并重写notice方法
 * 所有实现该接口的类命名方式为 ExceptionInformerFor...
 * 如 ExceptionInformerForLog
 * </pre>
 *
 * @author daiqi
 * @create 2018-07-31 11:35
 */
public abstract class BaseInformer {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通知
     */
    public boolean inform(String message) {
        return doInform(message);
    }

    /**
     * <p>
     * 执行通知
     * </p>
     *
     * @param message
     * @return boolean
     * @author daiqi
     * @date 2018/7/31 12:34
     */
    protected abstract boolean doInform(String message);

    public boolean inform(BaseThirdMessageDTO baseThirdMessageDTO, String webhook) {
        return true;
    }

}
