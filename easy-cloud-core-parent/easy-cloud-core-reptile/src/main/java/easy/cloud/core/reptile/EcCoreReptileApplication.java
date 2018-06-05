package easy.cloud.core.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.easy.cloud.core.basic.constant.EcBaseComponentScan;

import easy.cloud.core.reptile.dynamic.DynamicRuleTest;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { EcBaseComponentScan.COM_EASY_CLOUD})
@Configuration
public class EcCoreReptileApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(EcCoreReptileApplication.class, args);
		DynamicRuleTest.init();
	}
	
}
