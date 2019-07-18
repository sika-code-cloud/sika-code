package ${package.ErrorCodeEnum};

import com.sika.code.basic.errorcode.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * ${table.comment!} 错误枚举类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Getter
@AllArgsConstructor
public enum ${table.classBodyName}ErrorCodeEnum implements BaseErrorCode {
    /**
     * 错误枚举示例
     */
    ${table.classBodyName?upper_case}_ERROR_CODE_ENUM("${table.classBodyName?upper_case}_000001", "错误枚举示例")
    ;

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
}
