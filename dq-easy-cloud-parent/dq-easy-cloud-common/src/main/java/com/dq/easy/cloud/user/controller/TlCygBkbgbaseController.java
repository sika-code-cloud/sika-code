package com.dq.easy.cloud.user.controller;
import com.evada.inno.core.annotation.Rest;
import com.dq.easy.cloud.user.service.ITlCygBkbgbaseService;
import com.dq.easy.cloud.user.model.TlCygBkbgbase;
import com.dq.easy.cloud.user.dto.TlCygBkbgbaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import com.evada.inno.common.domain.ResultData;
import com.evada.inno.core.util.AssertUtils;

/**
* 描述：质量问题控制层
* @author THINK
* @date 2017/05/03
*/
@Rest(TlCygBkbgbase.class)
public class TlCygBkbgbaseController {

    @Autowired
    private ITlCygBkbgbaseService tlCygBkbgbaseService;

    /**
    * 描述：根据Id 查询
    * @param id  质量问题id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@PathVariable("id") String id)throws Exception {
        TlCygBkbgbaseDTO tlCygBkbgbaseDTO = tlCygBkbgbaseService.findDTOById(id);
        AssertUtils.checkResourceFound(tlCygBkbgbaseDTO);
        return new ResultData(TlCygBkbgbaseDTO.class, tlCygBkbgbaseDTO);
    }

    /**
    * 描述:创建质量问题
    * @param tlCygBkbgbaseDTO  质量问题DTO
    */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody TlCygBkbgbaseDTO tlCygBkbgbaseDTO) throws Exception {
        return new ResultData(TlCygBkbgbase.class,tlCygBkbgbaseService.createTlCygBkbgbase(tlCygBkbgbaseDTO));
    }

    /**
    * 描述：删除质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/{id}/bulk", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") String id) throws Exception {
        tlCygBkbgbaseService.deleteById(id);
    }

    /**
    * 描述：更新质量问题
    * @param id 质量问题id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData updateTlCygBkbgbase(@PathVariable("id") String id,@RequestBody TlCygBkbgbaseDTO tlCygBkbgbaseDTO) throws Exception {
        tlCygBkbgbaseDTO.setId(id);
        return new ResultData(TlCygBkbgbase.class,tlCygBkbgbaseService.updateTlCygBkbgbase(tlCygBkbgbaseDTO));
    }

}
