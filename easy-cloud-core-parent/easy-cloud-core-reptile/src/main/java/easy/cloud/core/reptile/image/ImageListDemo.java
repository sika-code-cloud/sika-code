package easy.cloud.core.reptile.image;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.HtmlBean;

@PipelineName("imageListDemo")
@Gecco(matchUrl = "http://book.51cto.com/art/201201/311871.htm", pipelines = "imageListDemo")
public class ImageListDemo implements HtmlBean, Pipeline<ImageListDemo> {

	private static final long serialVersionUID = -5583524962096502124L;
	@Image
	@HtmlField(cssPath = "img")
	public String pics;



	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	static class Item {
		@Attr(value = "src")
		@HtmlField(cssPath = "img")
		private String pic;

		public String getPic() {
			return pic;
		}

		public void setPic(String pic) {
			this.pic = pic;
		}
		
	}
	
	@Override
	public void process(ImageListDemo test) {
		System.out.println(test.getPics());
	}

	public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("easy.cloud.core.reptile.image")
                .start("http://book.51cto.com/art/201201/311871.htm")
                .interval(1000)
                .run();
	}
}