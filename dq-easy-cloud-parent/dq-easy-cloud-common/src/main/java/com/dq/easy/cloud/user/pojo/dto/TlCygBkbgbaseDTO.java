package com.dq.easy.cloud.user.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
 * 描述：质量问题模型
 * @author THINK
 * @date 2017/05/03
 */
@Entity
@Table(name="tl_cyg_bkbgbase_info")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class TlCygBkbgbase extends BaseModel implements ICreateListenable,IModifyListenable,IDeleteListenable {



}