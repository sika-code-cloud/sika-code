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

        // itemInfo
        Class<?> itemInfo = DynamicGecco.html().gecco("*")
                .stringField("url").csspath("dt.title a").href(false).build()
                .stringField("roleName").csspath("dt.title a span.name").text().build()
                .stringField("service").csspath("dd.server-and-time span.server-info").text().build()
                .stringField("price").csspath("div.item-opr p.price").text().build()
                .register();
        //对应的分页信息
        DynamicGecco.html().gecco("http://tl.cyg.changyou.com/goods/public?world_id=0&order_by=remaintime-desc&have_chosen=&page_num={pageNum}", "jdbcJsonPipeline")
                .stringField("pageNum").requestParameter().build()
                .requestField("request").request().build()
                .listField("list", itemInfo).csspath("div.jGoodsList ul#J_good_list.pg-goods-list li.role-item").build()
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
    public void testGithub() throws InterruptedException {

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
                .interval(10000)
                .run();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testTianYanCha() throws InterruptedException {
        //html body.font-c6b37072 div div.search_result_container div.new-border-bottom.pt5.pb5.ml15.mr15 div div.search_row_new_mobil div div.title span
        Class<?> itemInfo = DynamicGecco.html()
                .stringField("url").csspath("a.query_name").href(false).build()
                .stringField("legalName").csspath("a.legalPersonName").text().build()
                .stringField("registeredCapital").csspath("div.title span").text().build()
                .stringField("registeredTime").csspath("div.title:nth-child(3) span").text().build()
                .stringField("status").csspath("div.title:nth-child(4) span").text().build()
                .stringField("address").csspath("div.search_base").text().build()
                .register();
        //对应的分页信息
        DynamicGecco.html().gecco("*", "jdbcJsonPipeline")
                .requestField("request").request().build()
                .stringField("num").csspath(".unit").text(false).build()
                .listField("list", itemInfo).csspath("div.new-border-bottom").build()
                .register();


        //开始抓取
        GeccoEngine.create()
                .classpath("com.easy.cloud.core.reptile.common.pipeline")
                .start("https://m.tianyancha.com/search/p1?key=深圳市万服")
                .loop(true)
                .interval(10000)
                .run();
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static void main(String[] args) throws InterruptedException {
        new DynamicGeccoTest().testTianYanCha();
    }

}
