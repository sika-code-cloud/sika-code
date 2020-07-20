package me.zhyd.justauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@ControllerAdvice
@EnableCaching
@SpringBootApplication
public class JustauthDemoApplication implements ApplicationRunner {

    @Value("${server.port}")
    public int port;

    public static void main(String[] args) {
        SpringApplication.run(JustauthDemoApplication.class, args);
    }

    @RequestMapping("")
    public ModelAndView index() {
        Map<String, Object> map = new HashMap<>();
        map.put("enableAuthPlatforms", JustAuthPlatformInfo.getPlatformInfos());
        return new ModelAndView("index", map);
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Throwable e) {
        e.printStackTrace();
        return e.getMessage();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("已启动： http://localhost:" + port);
    }
}
