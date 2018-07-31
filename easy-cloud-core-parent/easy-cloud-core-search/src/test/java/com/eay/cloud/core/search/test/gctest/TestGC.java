package com.eay.cloud.core.search.test.gctest;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Title: TestGC
 * @Description:
 * @Author tudou
 * @Date 2018/7/28 20:58
 */
public class TestGC {
    public static void main(String[] args) {
        /*System.out.println(String.format("abds[%s];asdsads[%s]","test1","test2"));
        List<Map> list = new ArrayList<>();
        String s = null;
        Map map = new HashMap();
        for (int i = 0; i < 10; i++) {
            map.put("s",i);
//            s = i+"";
            list.add(map);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get("s"));
        }*/
//        String aa = "aa";
//        String bb = new StringBuilder("aa").toString();
//        System.out.println(aa == bb);
        TestGC testGC = new TestGC();
//        ecIndexTest.te();
        testGC.test_gc1();
//        ecIndexTest.te2();
//        ecIndexTest.test_gc2();
//        String a = new StringBuilder("aaa").append("aaa").toString();
//        System.out.println(a.intern() == a);
//        String b = new StringBuilder("bbb").append("bbb").toString();
//        System.out.println(b.intern() == b);
//        String b1 = new StringBuilder("bbb").append("bbb").toString();
//        System.out.println(b1.intern() == b1);
    }
    public void te(){
        String b = new StringBuilder("bbb").append("bbb").toString();
        System.out.println(b.intern() == b);
    }
    public void te2(){
        String b = new StringBuilder("bbb").append("bbb").toString();
        System.out.println(b.intern() == b);
    }

    private void test_gc1(){
        //在heap中创建内容为"wohenhao"的对象，并建立a到该对象的强引用，此时该对象时强可及
//        String a = new StringBuilder("bbb").append("bbb").toString();
//        String b = new StringBuilder("bbb").append("bbb").toString();
        String a = new StringBuilder("bbb").append("bbb").toString();
        String b = new StringBuilder("bbb").append("bbb").toString();
//        String b2 = new StringBuilder("bbb").append("bbb").toString();
        String c = new StringBuilder("bbb").append("bbb").toString();
        String d = new StringBuilder("bbb").append("bbb").toString();
//        System.out.println(a.intern() == a);
////        b.intern();
//        System.out.println(c.intern() == a);
//        System.out.println(c.intern() == c);
//        System.out.println(a == c);
//        String a=new String("wohenhao");
        //对heap中的对象建立软引用，此时heap中的对象仍然是强可及
        SoftReference< ?> softReference=new SoftReference<String>(a);
        //对heap中的对象建立弱引用，此时heap中的对象仍然是强可及
        WeakReference< ?> weakReference=new WeakReference<String>(a);
        System.out.println("强引用："+a+"\n软引用"+softReference.get()+"\n弱引用"+weakReference.get()+"\n");
        //heap中的对象从强可及到软可及
        a=null;
        System.out.println("强引用："+a+"\n软引用"+softReference.get()+"\n弱引用"+weakReference.get()+"\n");
        softReference.clear();//heap中对象从软可及变成弱可及,此时调用System.gc()，
        System.gc();
        System.out.println("强引用："+a+"\n软引用"+softReference.get()+"\n弱引用"+weakReference.get()+"\n");
//        System.out.println(b.intern() == b);
        b.intern();
        System.out.println(d.intern() == b);
        System.out.println(d.intern() == d);
//        System.out.println(c.intern() == c);
//        System.out.println(b.intern() == c);
//        System.out.println(d.intern() == c);
    }

    private void test_gc2(){
        //在heap中创建内容为"wohenhao"的对象，并建立a到该对象的强引用，此时该对象时强可及
        String a=new String("wohenhao");
        //对heap中的对象建立软引用，此时heap中的对象仍然是强可及
        SoftReference< ?> softReference=new SoftReference<String>(a);
        //对heap中的对象建立弱引用，此时heap中的对象仍然是强可及
        WeakReference< ?> weakReference=new WeakReference<String>(a);
        System.out.println("强引用："+a+"\n软引用"+softReference.get()+"\n弱引用"+weakReference.get()+"\n");
        a=null;//heap中的对象从强可及到软可及
        System.out.println("强引用："+a+"\n软引用"+softReference.get()+"\n弱引用"+weakReference.get()+"\n");
        System.gc();
        System.out.println("强引用："+a+"\n软引用"+softReference.get()+"\n弱引用"+weakReference.get()+"\n");
    }
}
