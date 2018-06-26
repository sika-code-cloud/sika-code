package com.easy.cloud.core.operator.sysuser.pojo.bo;

import com.easy.cloud.core.authority.utils.EcAuthorityUtils;
import com.easy.cloud.core.basic.pojo.bo.EcBaseBO;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author daiqi
 * @create 2018-06-25 13:59
 */
public class SysUserBO extends EcBaseBO{
    private SysUserDTO sysUserDTO;

    public SysUserBO(SysUserDTO sysUserDTO) {
        this.sysUserDTO = sysUserDTO;
    }

    public void buildRegisterData() {
        EcAssert.verifyObjNull(sysUserDTO);
        if (EcStringUtils.isEmpty(sysUserDTO.getSalt())) {
            sysUserDTO.setSalt(EcStringUtils.generateUUID());
        }
        String encryptPassword = EcAuthorityUtils.encryptOfMD5(sysUserDTO.getPassword(), sysUserDTO.getSalt());
        sysUserDTO.setPassword(encryptPassword);
    }

    public void verifyRegisterData() {
        EcAssert.verifyObjNull(sysUserDTO);
        EcAssert.verifyObjNull(sysUserDTO.getUsername(), "username");
        EcAssert.verifyObjNull(sysUserDTO.getPassword(), "password");
    }

    public SysUserDTO getSysUserDTO() {
        return sysUserDTO;
    }
}
