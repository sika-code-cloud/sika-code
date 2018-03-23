package ${package_name}.controller;
import com.evada.inno.core.annotation.Rest;
import ${package_name}.service.I${table_name}Service;
import ${package_name}.model.${table_name};
import ${package_name}.dto.${table_name}DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import com.evada.inno.common.domain.ResultData;
import com.evada.inno.core.util.AssertUtils;

/**
* 描述：${table_annotation}控制层
* @author ${author}
* @date ${date}
*/
@Rest(${table_name}.class)
public class ${table_name}Controller {

    @Autowired
    private I${table_name}Service ${table_name?uncap_first}Service;

    /**
    * 描述：根据Id 查询
    * @param id  ${table_annotation}id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData findById(@PathVariable("id") String id)throws Exception {
        ${table_name}DTO ${table_name?uncap_first}DTO = ${table_name?uncap_first}Service.findDTOById(id);
        AssertUtils.checkResourceFound(${table_name?uncap_first}DTO);
        return new ResultData(${table_name}DTO.class, ${table_name?uncap_first}DTO);
    }

    /**
    * 描述:创建${table_annotation}
    * @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
    */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData create(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        return new ResultData(${table_name}.class,${table_name?uncap_first}Service.create${table_name}(${table_name?uncap_first}DTO));
    }

    /**
    * 描述：删除${table_annotation}
    * @param id ${table_annotation}id
    */
    @RequestMapping(value = "/{id}/bulk", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") String id) throws Exception {
        ${table_name?uncap_first}Service.deleteById(id);
    }

    /**
    * 描述：更新${table_annotation}
    * @param id ${table_annotation}id
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultData update${table_name}(@PathVariable("id") String id,@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        ${table_name?uncap_first}DTO.setId(id);
        return new ResultData(${table_name}.class,${table_name?uncap_first}Service.update${table_name}(${table_name?uncap_first}DTO));
    }

}
