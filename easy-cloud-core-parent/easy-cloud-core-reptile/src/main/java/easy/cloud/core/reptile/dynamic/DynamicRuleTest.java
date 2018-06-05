package easy.cloud.core.reptile.dynamic;

import org.springframework.stereotype.Component;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;

/**
 * 本demo是一个在线修改抓取规则的例子，DyncmicGecco支持规则类的重新加载，不需要重启应用
 * 
 * @author huchengyi
 *
 */
@Component
public class DynamicRuleTest {

	public static void main(String[] args) throws Exception {
		init();
	}

	public static void init() throws Exception {
		// 初始化爬虫引擎，此时由于没有初始请求，爬虫引擎会阻塞初始队列，直到获取到初始请求
		GeccoEngine ge = GeccoEngine.create("com.geccocrawler.gecco.demo.jd").interval(2000).loop(true).thread(10)
				.engineStart();

		// 定义爬取规则
		Class<?> rule = DynamicGecco.html().gecco(new String[] { "https://github.com/xtuhcy/gecco" }, "consolePipeline")
				.stringField("title").csspath(".repository-meta-content").text(false).build().stringField("star")
				.csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
				// #js-repo-pjax-container >
				// div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav
				// > div > ul > li:nth-child(2) > div >
				// form.starred.js-social-form > a
				// #js-repo-pjax-container >
				// div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav
				// > div > ul
				// .intField("fork").csspath(".pagehead-actions li:nth-child(3)
				// .social-count").text().build()
				.intField("fork").csspath(".pagehead-actions > li:nth-child(3) > a:nth-child(2)").text().build()
				.loadClass();
		ge.getScheduler().into(new HttpGetRequest("https://github.com/xtuhcy/gecco"));

		// 注册规则
		ge.register(rule);
		// 定义爬取规则
		Class<?> rule1 = DynamicGecco.html()
				.gecco(new String[] { "https://github.com/dqeasycloud/easy-cloud" }, "testPipeline1")
				.stringField("title").csspath(".repository-meta-content").text(false).build().stringField("star")
				.csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
				// #js-repo-pjax-container >
				// div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav
				// > div > ul > li:nth-child(2) > div >
				// form.starred.js-social-form > a
				// #js-repo-pjax-container >
				// div.pagehead.repohead.instapaper_ignore.readability-menu.experiment-repo-nav
				// > div > ul
				// .intField("fork").csspath(".pagehead-actions li:nth-child(3)
				// .social-count").text().build()
				.intField("fork").csspath(".pagehead-actions > li:nth-child(3) > a:nth-child(2)").text().build()
				.loadClass();

		// 注册规则
		ge.register(rule1);
		// 加入初始请求，爬虫引擎开始工作
		ge.getScheduler().into(new HttpGetRequest("https://github.com/dqeasycloud/easy-cloud"));

		Thread.sleep(5000);

		System.out.println("修改规则");
		Class<?> newRule = null;
		try {
			// 开始更新规则
			ge.beginUpdateRule();
			// 修改规则
			newRule = DynamicGecco.html(rule.getName()).gecco("https://github.com/xtuhcy/gecco", "consolePipeline")
					// .intField("fork").csspath(".pagehead-actions
					// li:nth-child(3) .social-count").text().build()
					.removeField("star").loadClass();
			// 注册新规则
			ge.register(newRule);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 规则更新完毕
			ge.endUpdateRule();
		}

		Thread.sleep(5000);

		System.out.println("下线规则");
		try {
			// 开始更新规则
			ge.beginUpdateRule();
			// 下线之前的规则（也支持不下线规则，直接修改）
			ge.unregister(newRule);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 规则更新完毕
			ge.endUpdateRule();
		}
	}
}
