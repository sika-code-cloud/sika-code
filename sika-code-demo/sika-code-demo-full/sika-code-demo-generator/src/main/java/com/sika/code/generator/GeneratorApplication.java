package com.sika.code.generator;

import com.google.common.collect.Lists;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import com.sika.code.generator.dto.GeneratorClientDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorApplication {
    public static void main(String[] args) {
        GeneratorClientDTO clientDTO = new GeneratorClientDTO();
        clientDTO.setAuthor("sikadai");
        clientDTO.setTableNames(Lists.newArrayList("lf_test_temp"));

        generate(clientDTO);
    }

    private static void generate(GeneratorClientDTO clientDTO) {

        clientDTO.setTablePrefix("lf_");

        clientDTO.setProjectPrefix("sika-code-demo");
        clientDTO.setModulePackagePrefix("com.sika.code.demo");

        clientDTO.setIp("121.89.202.68");
        clientDTO.setPort(3306);
        clientDTO.setDbName("lf-admin");
        clientDTO.setDbUsername("root");
        clientDTO.setDbPassword("SikaDesignAdmin20201225");

        clientDTO.setGenerateController(true);
        clientDTO.setGenerateDomain(true);
        clientDTO.setApplicationClassName(SikaCodeDemoApplication.class.getName());
        clientDTO.setApplicationSimpleName(SikaCodeDemoApplication.class.getSimpleName());
        new GeneratorCommander().doGenerator(clientDTO);
    }

}
