package com.ruoyi.generator.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lancelot
 * @version 1.0
 * @date 2022/9/1 上午9:43
 */
class ClassFinderTest {

    @Test
    void findClassSet() {
        ClassFinder classFinder=new ClassFinder();
        classFinder.findClassSet("vm",(file)->{
            System.out.println("file = " + file);
        });

    }
}