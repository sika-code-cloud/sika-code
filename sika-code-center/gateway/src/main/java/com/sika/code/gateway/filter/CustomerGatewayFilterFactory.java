package com.sika.code.gateway.filter;

import cn.hutool.http.Method;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.common.thirdpart.constant.ThirdPartyConstant;
import com.sika.code.common.thirdpart.request.ThirdPartyRequestDTO;
import com.sika.code.common.thirdpart.util.ThirdPartyRequestUtil;
import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import com.sika.code.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义过滤器
 *
 * @author daiqi
 * @create 2019-08-26 22:59
 */
@Slf4j
public class CustomerGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomerGatewayFilterFactory.Config> {

    public CustomerGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("Config{}", JSONUtil.toJSONString(config));
        return (exchange, chain) -> {
//            long beginTime = DateUtil.current(false);
//            log.info("exchange：{}", JSONUtil.toJSONString(exchange.getAttributes()));
//            Mono<Void> mono = chain.filter(exchange);
//            long endTime = DateUtil.current(false);
//            log.info("所用的时间为: {}", (endTime - beginTime));
//            return mono;

            try {
                ThreadLocalManager.setThreadLocalAndInheritable(ThirdPartyConstant.HTTP_HEADER_NAME, exchange.getRequest().getHeaders());

                ThirdPartyRequestDTO thirdPartyRequestDTO = new ThirdPartyRequestDTO();
                thirdPartyRequestDTO.setRequestMethod(RequestMethod.POST);
                thirdPartyRequestDTO.setRequestUrlForFull("http://oauth-server/access_rule_type/list");
                Map<String, Object> retMap = new HashMap<>();
                retMap.put("queryRequest", new HashMap<>());
                thirdPartyRequestDTO.setData(retMap);
                Result result = ThirdPartyRequestUtil.requestForJson(thirdPartyRequestDTO, Result.class);
                log.info("请求的结果为：{}", JSONUtil.toJSONString(result));
                Result result1 = JSONUtil.parseObject(result, Result.class);
                log.info("result1:{}", JSONUtil.toJSONString(result1));
                final String REQUEST_TIME_BEGIN = "REQUEST_TIME_BEGIN";
                //记录请求开始时间
                exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
                return chain.filter(exchange).then(Mono.fromRunnable((Runnable) () -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        //打印
                        log.info(exchange.getRequest().getURI() + " 耗时" + (System.currentTimeMillis() - startTime));
                    }
                }));
            } finally {
                ThreadLocalManager.removeAuto();
            }

        };
    }

    public static class Config {

    }
}
