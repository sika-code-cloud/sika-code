package com.sika.code.demo.interfaces.common;

import com.sika.code.batch.standard.bean.common.BatchBean;
import com.sika.code.batch.standard.context.StandardContext;
import com.sika.code.batch.standard.entity.StandardBatchEntity;
import com.sika.code.core.result.Result;
import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/6/3 10:18
 */
@RestController
public class JobController extends BaseBizController {
    @Autowired
    protected StandardBatchEntity standardEntity;

    @RequestMapping("/custDoJob/anon")
    public Result custDoJob(@RequestBody BatchBean batchBean) {
        StandardContext standardContext = new StandardContext();
        try {
            standardEntity.execute(standardContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success("执行成功");
    }

    @GetMapping("/doRead/anon")
    public void doRead() throws IOException {
        ClassPathResource source = new ClassPathResource("data/user.csv");
        BufferedReader reader = new BufferedReader(new FileReader(source.getFile().getAbsolutePath()));
        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        System.out.println("读取文件的数量为：" + count);

    }
}