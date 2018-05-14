package easy.cloud.core.reptile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.spring.SpringGeccoEngine;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Configuration
public class EcCoreReptileApplication {
	@Autowired
	private static SpringGeccoEngine sge;
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
	
	@Bean
	public SpringGeccoEngine initGecco() {
		return new SpringGeccoEngine() {
			@Override
			public void init() {
				GeccoEngine.create()
				.pipelineFactory(springPipelineFactory)
				.classpath("easy.cloud.core.reptile")
				.start("https://github.com/xtuhcy/gecco")
				.interval(3000)
				.loop(true)
				.start();
			}
		};
	}
}
