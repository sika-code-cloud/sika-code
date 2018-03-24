package com.dq.easy.user;

import org.springframework.stereotype.Service;
import com.dq.easy.cloud.module.basic.pojo.bo.DqBaseBO;
import java.io.Serializable;
import javax.imageio.stream.ImageInputStream;

/**
 * 描述：测试 
 */
@Service(value = "userService")
public class UserDO extends DqBaseBO implements Serializable,ImageInputStream {

}
