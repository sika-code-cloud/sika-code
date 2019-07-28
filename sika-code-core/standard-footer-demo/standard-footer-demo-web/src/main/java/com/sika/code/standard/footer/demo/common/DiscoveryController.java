package com.sika.code.standard.footer.demo.common;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.sika.code.standard.rest.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("discovery")
public class DiscoveryController extends BaseStandardController {

    @NacosInjected
    private NamingService namingService;

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    @ResponseBody
    public String echo(@PathVariable String str) {
        String url = "http://service-provider/echo/" + str;
        return restTemplateUtil.selectRestTemplate(url).getForObject(url, String.class);
    }


}