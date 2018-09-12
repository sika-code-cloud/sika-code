package com.eay.cloud.core.search.test.gctest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: OOMObject
 * @Description:
 * @Author tudou
 * @Date 2018/8/12 15:16
 */
public class OOMObject {

    static class OOMObject2{
        public byte[] palceholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject2> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject2());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--");
        fillHeap(1000);
        System.out.println("--");
    }
}
