import com.sika.code.common.spring.SpringUtil;
import com.sika.code.standard.footer.demo.StandardFooterDemoApplication;
import com.sika.code.standard.footer.demo.common.mq.receiver.SayHello;
import com.sika.code.standard.footer.demo.common.mq.receiver.SayHelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StandardFooterDemoApplication.class)
@ContextConfiguration
public class SayHelloServiceTest {
    @Autowired
    private SayHelloService sayHelloService;
    @Autowired
    private SayHello sayHello;

    @Test
    public void test() {
        sayHelloService.test(SpringUtil.getBean(SayHello.class));
    }
}