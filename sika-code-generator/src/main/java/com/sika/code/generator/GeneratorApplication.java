package com.sika.code.generator;

import com.sika.code.generator.dto.GeneratorClientDTO;
import com.google.common.collect.Lists;
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

        clientDTO.setProjectPrefix("cat-reconciliation-platform");
        clientDTO.setModulePackagePrefix("com.cat.reconciliation.platform");

        clientDTO.setIp("121.89.202.68");
        clientDTO.setPort(3306);
        clientDTO.setDbName("lf-admin");
        clientDTO.setDbUsername("root");
        clientDTO.setDbPassword("SikaDesignAdmin20201225");

        clientDTO.setGenerateController(true);
        clientDTO.setGenerateDomain(true);
        GeneratorCommander.doGenerator(clientDTO);
    }

}
