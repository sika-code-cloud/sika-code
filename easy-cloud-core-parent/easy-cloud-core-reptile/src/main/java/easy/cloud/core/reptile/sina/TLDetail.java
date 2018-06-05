package easy.cloud.core.reptile.sina;

import java.util.List;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.geccocrawler.gecco.spring.SpringPipelineFactory;

@PipelineName(value = "TLDetail")
@Gecco(matchUrl = "http://tl.cyg.changyou.com/goods/char_detail?serial_num={serialNum}", pipelines = "TLDetail")
public class TLDetail implements HtmlBean, Pipeline<TLDetail> {

	@RequestParameter("serialNum")
	private String serialNum;

	@HtmlField(cssPath = "script")
	private List<String> script;
	
	public List<String> getScript() {
		return script;
	}

	public void setScript(List<String> script) {
		this.script = script;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void process(TLDetail bean) {
		for( String s : bean.getScript()) {
			if (s.contains("charObj")) {
				String [] values = s.split(";");
				String charObjValue = "";
				for (String value : values) {
					if (value.contains("charObj")) {
						charObjValue = value.split("=")[1];
					}
				}
				System.out.println("详情script数据为:" + charObjValue);
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		GeccoEngine.create()
		.classpath("easy.cloud.core.reptile.sina")
		.start("http://tl.cyg.changyou.com/goods/char_detail?serial_num=201805281738313284")
		.run();
	}

}
