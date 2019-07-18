package com.sika.code.standard.footer.demo.common.thirdpart.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daiqi
 * @create 2019-05-14 11:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ERPLoginRequest extends ErpRequest {
    private String loginName;
    private String password;


}
