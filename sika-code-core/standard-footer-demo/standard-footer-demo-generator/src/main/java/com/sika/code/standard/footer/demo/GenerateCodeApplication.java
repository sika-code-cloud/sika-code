package com.sika.code.standard.footer.demo;


import com.sika.code.GenerateCodeHandler;
import com.sika.code.GeneratorCodeDTO;

/**
 * 代码生成器启动类
 *
 * @author daiqi
 * @create 2019-05-09 15:32
 */
public class GenerateCodeApplication {
    private static String tableName = "risk_system_access_rule_type";
    private static String author = "daiqi";

    public static void main(String[] args) {
        GeneratorCodeDTO generatorCodeDTO = GenerateCodeConfig.buildGeneratorCodeDTO(tableName, author);
        new GenerateCodeHandler().generateCode(generatorCodeDTO);
    }
}
