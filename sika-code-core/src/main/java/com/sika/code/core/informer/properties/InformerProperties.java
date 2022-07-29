package com.sika.code.core.informer.properties;

import com.sika.code.core.informer.BaseInformer;
import lombok.Data;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-04-12 10:28
 */
@Data
public class InformerProperties {
    private List<BaseInformer> exceptionInformers;

}
