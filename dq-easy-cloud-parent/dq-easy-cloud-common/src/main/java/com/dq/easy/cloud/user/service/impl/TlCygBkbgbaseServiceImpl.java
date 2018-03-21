package com.dq.easy.cloud.user.service;
import com.evada.inno.core.service.IBaseService;
import com.dq.easy.cloud.user.model.TlCygBkbgbase;
import com.dq.easy.cloud.user.dto.TlCygBkbgbaseDTO;
/**
* 描述：质量问题 服务实现层接口
* @author THINK
* @date 2017/05/03
*/
public interface ITlCygBkbgbaseService extends IBaseService<TlCygBkbgbase,String> {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    TlCygBkbgbaseDTO findDTOById(String id)throws Exception;

    TlCygBkbgbaseDTO createTlCygBkbgbase(TlCygBkbgbaseDTO tlCygBkbgbaseDTO) throws Exception;

    void deleteTlCygBkbgbase(String id) throws Exception;

    TlCygBkbgbaseDTO updateTlCygBkbgbase(TlCygBkbgbaseDTO tlCygBkbgbaseDTO) throws Exception;

}
