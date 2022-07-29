package ${package.Entity};
import com.sika.code.core.base.convert.BaseConverter;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.PO}.${sikaEntityBodyName}PO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 转化类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${entity} extends BaseConverter {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    ${sikaEntityBodyName}Converter INSTANCE = Mappers.getMapper(${sikaEntityBodyName}Converter.class);

    /**
     * 将DTO对象转化为PO对象
     */
    ${sikaEntityBodyName}PO convertToPo(${sikaEntityBodyName}DTO dto);

    /**
     * 将PO对象转化为DTO对象
     */
    ${sikaEntityBodyName}DTO convertToDto(${sikaEntityBodyName}PO po);

    /**
     * 将DTO对象列表转化为PO对象列表
     */
    List<${sikaEntityBodyName}PO> convertToPos(List<${sikaEntityBodyName}DTO> dtos);

    /**
     * 将PO对象列表转化为DTO对象列表
     */
    List<${sikaEntityBodyName}DTO> convertToDtos(List<${sikaEntityBodyName}PO> pos);

}
