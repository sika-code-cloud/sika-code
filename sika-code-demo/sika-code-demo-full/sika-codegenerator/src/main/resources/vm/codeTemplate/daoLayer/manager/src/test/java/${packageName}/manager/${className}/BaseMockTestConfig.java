package ${packageName}.manager.${className};
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ComponentScan(basePackages = {"${packageName}"})
public class BaseMockTestConfig {

}