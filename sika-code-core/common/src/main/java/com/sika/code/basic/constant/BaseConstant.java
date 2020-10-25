package com.sika.code.basic.constant;


import com.sika.code.basic.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 基础常量类
 * </p>
 *
 * <pre>
 *  命名规范：各个模块常量类以constant为后缀
 *  使用示例：用户模块：用户状态
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月6日 下午1:46:21
 */
public class BaseConstant {

    /**
     * <p>
     * 删除枚举类
     * </p>
     *
     * @author daiqi
     * @创建时间 2018年6月8日 上午11:42:53
     */
    @Getter
    @AllArgsConstructor
    public enum IsDeletedEnum implements TypeEnumInf {
        /**
         * 删除状态---0---未删
         */
        NO(0, "未删"),
        /**
         * 删除状态---1---已删
         */
        YES(1, "已删");
        private Integer type;
        private String desc;

    }

    @Getter
    @AllArgsConstructor
    public enum AvailableEnum implements TypeEnumInf {
        /**
         * 可用状态---0---不可用
         */
        NO(0, "不可用"),
        /**
         * 可用状态---1---可用
         */
        YES(1, "可用");
        private Integer type;
        private String desc;

        public static boolean available(Integer available) {
            if (available == null) {
                return false;
            }
            return BaseUtil.equals(available, YES.type);
        }


        public static boolean notAvailable(Integer available) {
            return !available(available);
        }

    }

    /**
     * <p>
     * 字符集类型常量类
     * </p>
     *
     * @author daiqi 创建时间 2018年2月23日 下午2:08:00
     */
    public static class Charset {
        public static final String UTF_8 = "UTF-8";
        public static final String GBK = "GBK";
        public static final String ISO_8859_1 = "ISO-8859-1";
        public static final String ISO8859_1 = "iso8859-1";
        public static final String UNICODE = "UNICODE";
        public static final String ASCII = "ASCII";
    }

    /**
     * 图片格式常量类
     */
    public static class ImageFormat {
        public static final String BMP = "BMP";
        public static final String GIF = "GIF";
        public static final String EPS = "EPS";
        public static final String DCS = "DCS";
        public static final String JPEG = "JPEG";
        public static final String JPG = "JPG";
        public static final String JPE = "JPE";
        public static final String PCX = "PCX";
        public static final String PDF = "PDF";
        public static final String PNG = "PNG";
        public static final String TIFF = "TIFF";
        public static final String PXR = "PXR";
    }

    @Getter
    @AllArgsConstructor
    public enum BooleanEnum implements TypeEnumInf {
        /**
         * boolean枚举雷 --- 否 --- 0
         */
        NO(0, "否"),
        /**
         * boolean枚举雷 --- 是 --- 1
         */
        YES(1, "是");
        private Integer type;
        private String desc;

    }
}
