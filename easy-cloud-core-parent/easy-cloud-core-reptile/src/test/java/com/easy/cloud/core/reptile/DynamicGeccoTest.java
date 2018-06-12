package com.easy.cloud.core.reptile;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.junit.Test;

/**
 * @author daiqi
 * @create 2018-06-12 9:05
 */
public class DynamicGeccoTest {
    @Test
    public void testPageAndDetail() {
//        http://tl.cyg.changyou.com/goods/char_detail?serial_num=201806121144334550

        //对应JDPrice类
        DynamicGecco.html().gecco("http://tl.cyg.changyou.com/goods/char_detail?serial_num={serialNum}", "redisJsonPipeline")
                .stringField("serialNum").requestParameter().build()
                .stringField("service").csspath("div.goods-info ul.info-list li p.server-info.J-message").text().build()
//                .floatField("roleDetail").csspath("html body.body script").text().build()
                .floatField("menpai").csspath("div#goods-detail.goods-detail div.tab-cont-item.role div.left.w323 div.role-show span.fn-other-menpai").text().build()
                .register();

        Class<?> listInfo = DynamicGecco.html()
                .stringField("url").csspath("dt.title a").href(false).build()
                .stringField("roleName").csspath("dt.title a span.name").text().build()
                .stringField("service").csspath("dd.server-and-time span.server-info").text().build()
                .stringField("price").csspath("div.item-opr p.price").text().build()
                .register();
        //对应ProductDetail类
        DynamicGecco.html().gecco("http://tl.cyg.changyou.com/goods/public?world_id=0&order_by=remaintime-desc&have_chosen=&page_num={pageNum}", "jdbcJsonPipeline")
                .stringField("pageNum").requestParameter().build()
                .stringField("serialNum").requestParameter().build()
                .requestField("request").request().build()
                .listField("list", listInfo).csspath("div.jGoodsList ul#J_good_list.pg-goods-list li.role-item").build()
                .register();

        HttpGetRequest start = new HttpGetRequest("http://tl.cyg.changyou.com/goods/public?world_id=0&order_by=remaintime-desc&have_chosen=&page_num=1");
//        HttpGetRequest start = new HttpGetRequest("http://tl.cyg.changyou.com/goods/char_detail?serial_num=201806121144334550");
        GeccoEngine.create()
                .classpath("com.easy.cloud.core.reptile.common.pipeline")
                .start(start)
                .interval(2000)
                .loop(false)
                .run();
    }

    @Test
    public void testGithub() {
        DynamicGecco.html()
                .gecco("https://github.com/xtuhcy/gecco", "consolePipeline")
                .stringField("title").csspath(".repository-meta-content").text(false).build()
                .stringField("star").csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
                .stringField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
                .register();

        //开始抓取
        GeccoEngine.create()
                .classpath("com.geccocrawler.gecco.demo.dynamic")
                .start("https://github.com/xtuhcy/gecco")
                .loop(true)
                .interval(1000)
                .run();
    }

    public static void main(String[] args) {
        new DynamicGeccoTest().testPageAndDetail();
    }

}
