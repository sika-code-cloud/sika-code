package com.zyd.blog.util;

import cn.hutool.core.io.resource.FileResource;
import org.junit.Test;
import org.lionsoul.ip2region.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author daiqi
 * @create 2020-09-30 13:05
 */
public class TestIpToCity {

    @Test
    public void testSearch() {
        String[] argv = {"E:\\project\\sikacodegitee\\sika-code\\open-project1\\DBlog\\blog-core\\src\\main\\resources\\ip2region.db", "memory"};
        String line = "218.17.197.172";
        if (argv.length == 0) {
            System.out.println("| Usage: java -jar ip2region-{version}.jar [ip2region db file]");
            return;
        }
        File file = new FileResource(argv[0]).getFile();
        if (file.exists() == false) {
            System.out.println("Error: Invalid ip2region.db file");
            return;
        }

        int algorithm = DbSearcher.BTREE_ALGORITHM;
        String algoName = "B-tree";
        if (argv.length > 1) {
            if (argv[1].equalsIgnoreCase("binary")) {
                algoName = "Binary";
                algorithm = DbSearcher.BINARY_ALGORITHM;
            } else if (argv[1].equalsIgnoreCase("memory")) {
                algoName = "Memory";
                algorithm = DbSearcher.MEMORY_ALGORITYM;
            }
        }

        try {
            System.out.println("initializing " + algoName + " ... ");
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, argv[0]);

            //define the method
            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            System.out.println("+----------------------------------+");
            System.out.println("| ip2region test shell             |");
            System.out.println("| Author: chenxin619315@gmail.com  |");
            System.out.println("| Type 'quit' to exit program      |");
            System.out.println("+----------------------------------+");

            double sTime = 0, cTime = 0;
            DataBlock dataBlock = null;
            System.out.print("ip2region>> ");
            if (Util.isIpAddress(line) == false) {
                System.out.println("Error: Invalid ip address");
            }
            sTime = System.nanoTime();
            for (int i = 0 ; i < 10000000; ++i) {
                dataBlock = (DataBlock) method.invoke(searcher, line);
            }
            cTime = (System.nanoTime() - sTime) / 1000000;
            System.out.printf("%s in %.5f millseconds\n", dataBlock, cTime);
            searcher.close();
            System.out.println("+--Bye");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DbMakerConfigException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
