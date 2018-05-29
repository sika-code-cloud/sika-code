package easy.cloud.core.reptile.common;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

public class CommonCrawler implements HtmlBean {

	private static final long serialVersionUID = -8870768223740844229L;

	@Request
	private HttpRequest request;
	
	@Text(own=false)
	@HtmlField(cssPath="body")
	private String body;

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public static void main(String[] args) {
		GeccoEngine.create()
		.classpath("easy.cloud.core.reptile.common")
		.start("https://github.com/xtuhcy/gecco/blob/master/src/test/java/com/geccocrawler/gecco/demo/CommonCrawler.java")
		.interval(2000)
		.debug(false)
		.start();
	}
}