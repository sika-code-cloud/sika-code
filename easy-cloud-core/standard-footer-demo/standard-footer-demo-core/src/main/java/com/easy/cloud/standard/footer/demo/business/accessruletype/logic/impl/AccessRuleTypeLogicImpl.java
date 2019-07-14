package com.easy.cloud.standard.footer.demo.business.accessruletype.logic.impl;


import com.easy.cloud.basic.util.BaseUtil;
import com.easy.cloud.common.date.util.DateUtil;
import com.easy.cloud.common.thread.factory.Executors;
import com.easy.cloud.common.thread.factory.ThreadFactory;
import com.easy.cloud.common.threadlocal.manager.ThreadLocalManager;
import com.easy.cloud.mq.rabbit.producer.sender.RabbitSender;
import com.easy.cloud.mq.rabbit.producer.sender.dto.RabbitSenderDTO;
import com.easy.cloud.standard.base.logic.BaseStandardLogic;
import com.easy.cloud.standard.footer.demo.business.accessruletype.logic.AccessRuleTypeLogic;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeCommonQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeListQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypePageQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveBatchRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.update.AccessRuleTypeUpdateByIdRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeListQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypePageQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.save.AccessRuleTypeSaveResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.update.AccessRuleTypeUpdateResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import com.easy.cloud.standard.footer.demo.common.demo.constant.DemoConstant;
import com.easy.cloud.standard.footer.demo.common.demo.util.DemoUtil;
import com.easy.cloud.standard.footer.demo.common.mq.constant.queue.DemoMqExchangeConfigEnum;
import com.easy.cloud.standard.footer.demo.common.mq.constant.queue.DemoMqQueueConfigEnum;
import com.google.common.collect.Lists;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * 准入规则类型表 逻辑实现类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Component(value = "accessRuleTypeLogic")
public class AccessRuleTypeLogicImpl extends BaseStandardLogic implements AccessRuleTypeLogic {
    @Autowired
    private AccessRuleTypeService accessRuleTypeService;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ExecutorService executorService = Executors.newFixedThreadPool(5, new ThreadFactory("test-name"));

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessRuleTypeSaveResponseBO save(AccessRuleTypeSaveRequestBO request) {
        return request.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessRuleTypeSaveResponseBO saveBatch(AccessRuleTypeSaveBatchRequestBO requestBO) {
        return requestBO.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessRuleTypeUpdateResponseBO updateById(AccessRuleTypeUpdateByIdRequestBO request) {
        return request.execute();
    }

    @Override
    public AccessRuleTypePageQueryResponseBO page(AccessRuleTypePageQueryRequestBO request) {
        Integer count = request.getQueryRequest().getPageSize();
        if (BaseUtil.isNull(count)) {
            count = 1000000;
        }
//        for (int i = 0; i < 2; ++i) {
//            testAddCreditReportNo(no, count);
//            testAddCreditReportNo1(no, count);
//            testAddCreditReportNo2(no, count);
//            testGetCreditReportNo(count);
//            testGetCreditReportNo1(count);
//            testGetCreditReportNo2(count);
//            log.info("\t\n");
//        }

        return request.execute();
    }

    @Override
    public AccessRuleTypeQueryResponseBO find(AccessRuleTypeCommonQueryRequestBO request) {
        List<AccessRuleTypeCommonQueryRequestBO.AccessRuleTypeCommonQueryRequest> list = Lists.newArrayList(request.getQueryRequest());
//        rabbitSender.convertAndSend(DemoMqQueueConfigEnum.QUEUE, list);
//        rabbitSender.convertAndSend(DemoMqQueueConfigEnum.QUEUE2, request.getQueryRequest());
//        rabbitSender.convertAndSend(DemoMqExchangeConfigEnum.EXCHANGE1, DemoMqQueueConfigEnum.QUEUE4, request.getQueryRequest());
//        executorService.submit(() -> {
//            ErpRequestUtil.postErp(new ErpRequest(), ErpThirdPartyInterfaceEnum.LOGIN);
//        });
        AccessRuleTypeQueryResponseBO responseBO = request.execute();
        responseBO.setStartTime(DateUtil.current(false));
        for (int i = 0; i < request.getQueryRequest().getPageSize(); ++i) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            RabbitSenderDTO rabbitSenderDTO = new RabbitSenderDTO()
                    .setExchangeConfigEnum(DemoMqExchangeConfigEnum.EXCHANGE2)
                    .setQueueConfigEnum(DemoMqQueueConfigEnum.QUEUE3)
                    .setData(responseBO);
            rabbitSender.convertAndSend(rabbitSenderDTO);
//            rabbitTemplate.convertAndSend(DemoMqExchangeConfigEnum.EXCHANGE.getName(), DemoMqQueueConfigEnum.QUEUE3.getName(), responseBO);
        }

//        rabbitSender.convertAndSend(DemoMqExchangeConfigEnum.EXCHANGE1, DemoMqQueueConfigEnum.QUEUE3, responseBO);
//        rabbitSender.convertAndSend(DemoMqExchangeConfigEnum.EXCHANGE31, DemoMqQueueConfigEnum.QUEUE3, responseBO);
        responseBO.setEndTime(DateUtil.current(false));
        return responseBO;
    }

    @Override
    public AccessRuleTypeListQueryResponseBO list(AccessRuleTypeListQueryRequestBO request) {
        return request.execute();
    }

    public void testAddCreditReportNo(String no, int count) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            DemoUtil.addCreditReportNo(no);
        }
        log.info("testAddCreditReportNo所用的时间为:" + (System.currentTimeMillis() - start));
    }

    public void testAddCreditReportNo1(String no, int count) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            ThreadLocalManager.set(DemoConstant.CREDIT_REPORT_NO_NAME, no);
        }
        log.info("testAddCreditReportNo1所用的时间为:" + (System.currentTimeMillis() - start));
    }

    public void testAddCreditReportNo2(String no, int count) {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            ThreadLocalManager.setInheritable(DemoConstant.CREDIT_REPORT_NO_NAME, no);
        }
        log.info("testAddCreditReportNo2所用的时间为:" + (System.currentTimeMillis() - start));
    }

    public void testGetCreditReportNo(int count) {
        Long start = System.currentTimeMillis();
        String no = null;
        for (int i = 0; i < count; ++i) {
            no = DemoUtil.getCreditReportNo();
        }
        log.info("testAddCreditReportNo所用的时间为:" + (System.currentTimeMillis() - start) + ":" + no);
    }

    public void testGetCreditReportNo1(int count) {
        String no = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            no = (String) ThreadLocalManager.get(DemoConstant.CREDIT_REPORT_NO_NAME);
        }
        log.info("testAddCreditReportNo1所用的时间为:" + (System.currentTimeMillis() - start) + ":" + no);
    }

    public void testGetCreditReportNo2(int count) {
        String no = null;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            no = (String) ThreadLocalManager.getInheritable(DemoConstant.CREDIT_REPORT_NO_NAME);
        }
        log.info("testAddCreditReportNo2所用的时间为:" + (System.currentTimeMillis() - start) + ":" + no);
    }
}