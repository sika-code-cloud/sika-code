package com.sika.code.standard.footer.demo.common.mq.receiver;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * @author daiqi
 * @create 2020-06-13 21:38
 */
@Slf4j
public class Test1 {
    public static void main(String[] args) {
    }

    public static void test2() {
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000).forEach(i -> {
            try {
                Files.lines(Paths.get("E:\\demo.txt")).forEach(line -> {
                    longAdder.increment();
                    System.out.println(longAdder.longValue());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        log.info("total : {}", longAdder.longValue());
    }


    private static void perByteOperation() throws IOException {
        try (FileInputStream fileInputStream =
                     new FileInputStream("E://src.txt");
             FileOutputStream fileOutputStream =
                     new FileOutputStream("E://dest.txt")) {
            int i;
            while ((i = fileInputStream.read()) != -1) {
                fileOutputStream.write(i);
            }
        }
    }


    private static void bufferOperationWith100Buffer() throws IOException {
        try (FileInputStream fileInputStream =
                     new FileInputStream("E://src.txt");
             FileOutputStream fileOutputStream =
                     new FileOutputStream("E://dest.txt")) {
            byte[] buffer = new byte[1000];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }
    }


    public class ReflectionIssueApplication {
        private void age(int age) {
            System.out.println("int age = " + age);
        }

        private void age(Integer age) {
            System.out.println("Integer age = " + age);
        }
    }
}
