package com.sika.code.informer.properties;

import com.sika.code.informer.BaseInformer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author daiqi
 * @create 2019-04-12 10:28
 */
@Data
@Builder
public class InformerProperties {
    private List<BaseInformer> exceptionInformers;

}
