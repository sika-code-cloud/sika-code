package easy.cloud.core.reptile.sina;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.spider.HtmlBean;

@PipelineName("SinaList")
@Gecco(matchUrl = "http://tl.cyg.changyou.com?page_num={currentPage}", pipelines = "SinaList")
public class SinaList implements HtmlBean, Pipeline<SinaList> {

	private static final long serialVersionUID = 6683895914723213684L;
	@Request
	private HttpRequest httpRequest;

	@Text
	@HtmlField(cssPath = "div.jGoodsList > div > a.num.active > span")
	private int currentPage;

	@HtmlField(cssPath = ".jGoodsList #J_good_list .role-item")
	private List<Item> items;

	@HtmlField(cssPath = "script")
	private List<String> script;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<String> getScript() {
		return script;
	}

	public void setScript(List<String> script) {
		this.script = script;
	}

	public HttpRequest getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public static class Item implements HtmlBean {

		private static final long serialVersionUID = 5243013123370386328L;

		@Attr(value = "href")
		@HtmlField(cssPath = ".item-img a")
		// @Href(click = true)
		private String url;

		@Text
		@HtmlField(cssPath = ".item-opr .price")
		private String price;

		@Text
		@HtmlField(cssPath = "a")
		private String tag;

		@Text
		@HtmlField(cssPath = ".title .name")
		private String name;

		@Text
		@HtmlField(cssPath = ".title a")
		private String nickName;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

	}

	@Override
	public void process(SinaList productList) {
		System.out.println("数据为：" + JSONArray.toJSONString(productList));
		// for( String s : productList.getScript()) {
		// if (s.contains("SERVER_INFO")) {
		// String serverInfoVal = s.split("=")[1].trim().replace(";", "");
		//
		// System.out.println("script数据为:" + serverInfoVal);
		// JSONArray jsonArray = JSONArray.parseArray(serverInfoVal);
		// System.out.println("数据条数为：" + jsonArray.size());
		// for (Object obj : jsonArray) {
		// System.out.println(obj);
		// }
		// }
		// }
		HttpRequest currRequest = productList.getHttpRequest();
		// 下一页继续抓取
		int currPage = productList.getCurrentPage();
		int nextPage = currPage + 1;
		if (currPage < 10) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			if (currUrl.indexOf("page_num=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "page_num=" + currPage, "page_num=" + nextPage);
			} else {
				nextUrl = currUrl + "?" + "page_num=" + nextPage;
			}
			DeriveSchedulerContext.into(currRequest.subRequest(nextUrl));
		} else {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
//	body > div.pg-wrapper > div.pg-body > div.pg-buy.ui-cnt > div > div.ui-cnt-main-wrapper > div.ui-cnt-main-bd.fix-goods-list > div.ui-cnt-main > div.pg-goods-main > div.jGoodsList > div > a.num.active > span
//	body > div.pg-wrapper > div.pg-body > div.pg-buy.ui-cnt > div > div.ui-cnt-main-wrapper > div.ui-cnt-main-bd.fix-goods-list > div.ui-cnt-main > div.pg-goods-main > div.jGoodsList > div > a:nth-child(12) > span
	public static void main(String[] args) {
		GeccoEngine.create()
		.classpath("easy.cloud.core.reptile.sina")
		.start("http://tl.cyg.changyou.com?page_num=1", "http://tl.cyg.changyou.com/goods/char_detail?serial_num=201805281738313284")
		.thread(1)
		// 单个爬虫每次抓取完一个请求后的间隔时间
		.interval(1000)
		.loop(true)
		// 使用pc端userAgent
		.mobile(false)
		.debug(false)
		.start();
	}
}