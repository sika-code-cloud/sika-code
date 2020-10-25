package com.sika.code.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lk
 * @Description: 身份证工具类
 * @date 2018/4/13 19:27
 */
public class IDCardNoUtil {
    /**
     * 中国公民身份证号码最小长度。
     */
    private static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     * 中国公民身份证号码最大长度。
     */
    private static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 17位系数，每位加权因子
     */
    public static final int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9,
            10, 5, 8, 4, 2};
    /**
     * 台湾身份首字母对应数字
     */
    private static Map<String, Integer> twFirstCode = new HashMap<>();
    /**
     * 香港身份首字母对应数字
     */
    private static Map<String, Integer> hkFirstCode = new HashMap<>();

    static {
        twFirstCode.put("A", 10);
        twFirstCode.put("B", 11);
        twFirstCode.put("C", 12);
        twFirstCode.put("D", 13);
        twFirstCode.put("E", 14);
        twFirstCode.put("F", 15);
        twFirstCode.put("G", 16);
        twFirstCode.put("H", 17);
        twFirstCode.put("J", 18);
        twFirstCode.put("K", 19);
        twFirstCode.put("L", 20);
        twFirstCode.put("M", 21);
        twFirstCode.put("N", 22);
        twFirstCode.put("P", 23);
        twFirstCode.put("Q", 24);
        twFirstCode.put("R", 25);
        twFirstCode.put("S", 26);
        twFirstCode.put("T", 27);
        twFirstCode.put("U", 28);
        twFirstCode.put("V", 29);
        twFirstCode.put("X", 30);
        twFirstCode.put("Y", 31);
        twFirstCode.put("W", 32);
        twFirstCode.put("Z", 33);
        twFirstCode.put("I", 34);
        twFirstCode.put("O", 35);

        hkFirstCode.put("A", 1);
        hkFirstCode.put("B", 2);
        hkFirstCode.put("C", 3);
        hkFirstCode.put("R", 18);
        hkFirstCode.put("U", 21);
        hkFirstCode.put("Z", 26);
        hkFirstCode.put("X", 24);
        hkFirstCode.put("W", 23);
        hkFirstCode.put("O", 15);
        hkFirstCode.put("N", 14);
    }


    /**
     * 根据身份证号码计算年龄
     *
     * @param idCardNo
     * @return
     */

    public static Integer getAgeByIdCard(String idCardNo) {
        int length = idCardNo.length();
        String dates = "";
        if (length == 18) {
            dates = idCardNo.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year) - Integer.parseInt(dates);
            return u;
        } else {
            if (length == 15) {
                dates = idCardNo.substring(6, 8);
                return Integer.parseInt(dates);
            } else {
                return 0;
            }
        }
    }

    /**
     * 根据身份证号码计算出生日期
     *
     * @param idCardNo
     * @return
     */

    public static Date getBirthdayByIdCard(String idCardNo) {
        if (idCardNo.length() != 18) {
            return null;
        }
        //通过String[]的subString方法来读取信息
        String yyyy = idCardNo.substring(6, 10);//出生的年份
        String mm = idCardNo.substring(10, 12);
        String dd = idCardNo.substring(12, 14);
        String birth = yyyy.concat("-").concat(mm).concat("-").concat(dd);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthday;
    }

    /**
     * 校验身份证
     *
     * @param idCardNo
     * @return
     */
    public static boolean isIDCardNo(String idCardNo) {
        //最后一位身份证的号码
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String Ai = "";
        //=============号码长度15位或18位
        if (idCardNo.length() != 15 && idCardNo.length() != 18) {
            return false;
        }
        //============前17位应为纯数字
        if (idCardNo.length() == 18) {
            Ai = idCardNo.substring(0, 17);
        } else if (idCardNo.length() == 15) {
            Ai = idCardNo.substring(0, 6) + "19" + idCardNo.substring(6, 15);//中间拼的“19”是年数“19**”年
        }
        if (isNumeric(Ai) == false) {
            return false;
        }
        //===========出生年月是否有效
        String IdCardNoYear = Ai.substring(6, 10);
        String IdCardNoMonth = Ai.substring(10, 12);
        String IdCardNoDay = Ai.substring(12, 14);
        if (isDate(IdCardNoYear + "-" + IdCardNoMonth + "-" + IdCardNoDay) == false) {
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(IdCardNoYear)) > 150 || (gc.getTime().getTime() - sdf.parse(IdCardNoYear + "-" +
                    IdCardNoMonth + "-" + IdCardNoDay).getTime()) < 0) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        } catch (ParseException e) {
            return false;
        }

        //月份范围1-12
        if (Integer.parseInt(IdCardNoMonth) > 12 || Integer.parseInt(IdCardNoMonth) == 0) {
            return false;
        }
        //日期范围1-31
        if (Integer.parseInt(IdCardNoDay) > 31 || Integer.parseInt(IdCardNoDay) == 0) {
            return false;
        }
        //========地区码
        if (provinceMap.get(Ai.substring(0, 2)) == null) {
            return false;
        }
        //======判断最后一位值
        int totalmulAiCoefficient = 0;
        for (int i = 0; i < 17; i++) {
            totalmulAiCoefficient = totalmulAiCoefficient + Integer.parseInt(String.valueOf(Ai.charAt(i))) * power[i];
        }
        int modValue = totalmulAiCoefficient % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;
        if (idCardNo.length() == 18) {
            if (Ai.equalsIgnoreCase(idCardNo) == false) {
                return false;
            }
        } else {
            return true;
        }
        return true;
    }


    /**
     * 判断字符串是否为数字
     *
     * @param ai
     * @return
     */

    private static boolean isNumeric(String ai) {

        // TODO Auto-generated method stub

        Pattern pattern = Pattern.compile("[0-9]*");

        Matcher isNum = pattern.matcher(ai);

        if (isNum.matches()) {

            return true;

        } else {

            return false;

        }

    }

    /**
     * 判断字符串是否为日期格式
     *
     * @return
     */

    private static boolean isDate(String strDate) {

        // TODO Auto-generated method stub

        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");

        Matcher matcher = pattern.matcher(strDate);

        if (matcher.matches()) {

            return true;

        } else {

            return false;

        }

    }

    private static Map<String, String> provinceMap = new HashMap();

    static {
        provinceMap.put("11", "北京");
        provinceMap.put("12", "天津");
        provinceMap.put("13", "河北");
        provinceMap.put("14", "山西");
        provinceMap.put("15", "内蒙古");
        provinceMap.put("21", "辽宁");
        provinceMap.put("22", "吉林");
        provinceMap.put("23", "黑龙江");
        provinceMap.put("31", "上海");
        provinceMap.put("32", "江苏");
        provinceMap.put("33", "浙江");
        provinceMap.put("34", "安徽");
        provinceMap.put("35", "福建");
        provinceMap.put("36", "江西");
        provinceMap.put("37", "山东");
        provinceMap.put("41", "河南");
        provinceMap.put("42", "湖北");
        provinceMap.put("43", "湖南");
        provinceMap.put("44", "广东");
        provinceMap.put("45", "广西");
        provinceMap.put("46", "海南");
        provinceMap.put("50", "重庆");
        provinceMap.put("51", "四川");
        provinceMap.put("52", "贵州");
        provinceMap.put("53", "云南");
        provinceMap.put("54", "西藏");
        provinceMap.put("61", "陕西");
        provinceMap.put("62", "甘肃");
        provinceMap.put("63", "青海");
        provinceMap.put("64", "宁夏");
        provinceMap.put("65", "新疆");
        provinceMap.put("71", "台湾");
        provinceMap.put("81", "香港");
        provinceMap.put("82", "澳门");
        provinceMap.put("91", "国外");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param iArr
     * @return 身份证编码。
     */
    public static int getPowerSum(int[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        iSum = iSum + iArr[i] * power[j];
                    }
                }
            }
        }
        return iSum;
    }

    /**
     * 将power和值与11取模获得余数进行校验码判断
     *
     * @param iSum
     * @return 校验位
     */
    public static String getCheckCode18(int iSum) {
        String sCode = "";
        switch (iSum % 11) {
            case 10:
                sCode = "2";
                break;
            case 9:
                sCode = "3";
                break;
            case 8:
                sCode = "4";
                break;
            case 7:
                sCode = "5";
                break;
            case 6:
                sCode = "6";
                break;
            case 5:
                sCode = "7";
                break;
            case 4:
                sCode = "8";
                break;
            case 3:
                sCode = "9";
                break;
            case 2:
                sCode = "x";
                break;
            case 1:
                sCode = "0";
                break;
            case 0:
                sCode = "1";
                break;
        }
        return sCode;
    }

    /**
     * 将字符数组转换成数字数组
     *
     * @param ca 字符数组
     * @return 数字数组
     */
    public static int[] convertCharToInt(char[] ca) {
        int len = ca.length;
        int[] iArr = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /**
     * 将15位身份证号码转换为18位
     *
     * @param idCard 15位身份编码
     * @return 18位身份编码
     */
    public static String convert15CardTo18(String idCard) {
        String idCard18 = "";
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return null;
        }
        // 获取出生年月日
        String birthday = idCard.substring(6, 12);
        Date birthDate = null;
        try {
            birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (birthDate != null) {
            cal.setTime(birthDate);
        }
        // 获取出生年(完全表现形式,如：2010)
        String sYear = String.valueOf(cal.get(Calendar.YEAR));
        idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
        // 转换字符数组
        char[] cArr = idCard18.toCharArray();
        if (cArr != null) {
            int[] iCard = convertCharToInt(cArr);
            int iSum17 = getPowerSum(iCard);
            // 获取校验位
            String sVal = getCheckCode18(iSum17);
            if (sVal.length() > 0) {
                idCard18 += sVal;
            } else {
                return null;
            }
        }
        return idCard18;
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(0 - 女 ， 1 - 男)
     */
    public static Integer getGenderByIdCard(String idCard) {
        Integer sex = 0;
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sex = 1;
        }
        return sex;
    }

    /**
     * 根据身份编号获取户籍省份
     *
     * @param idCard 身份编码
     * @return 省级编码。
     */
    public static String getProvinceByIdCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        String sProvinNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            sProvinNum = idCard.substring(0, 2);
        }
        sProvince = provinceMap.get(sProvinNum);
        return sProvince;
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Integer getYearByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        return Integer.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard 身份编号
     * @return 生日(MM)
     */
    public static Integer getMonthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        return Integer.valueOf(idCard.substring(10, 12));
    }


    /**
     * 根据身份编号获取生日天
     *
     * @param idCard 身份编号
     * @return 生日(dd)
     */
    public static Integer getDayByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = convert15CardTo18(idCard);
        }
        return Integer.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份证号，自动获取对应的星座
     *
     * @param idCard 身份证号码
     * @return 星座
     */
    public static String getConstellationById(String idCard) {

        int month = getMonthByIdCard(idCard);
        int day = getDayByIdCard(idCard);
        String strValue = "";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) {
            strValue = "水瓶座";
        } else if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) {
            strValue = "双鱼座";
        } else if ((month == 3 && day > 20) || (month == 4 && day <= 19)) {
            strValue = "白羊座";
        } else if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) {
            strValue = "金牛座";
        } else if ((month == 5 && day >= 21) || (month == 6 && day <= 21)) {
            strValue = "双子座";
        } else if ((month == 6 && day > 21) || (month == 7 && day <= 22)) {
            strValue = "巨蟹座";
        } else if ((month == 7 && day > 22) || (month == 8 && day <= 22)) {
            strValue = "狮子座";
        } else if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) {
            strValue = "处女座";
        } else if ((month == 9 && day >= 23) || (month == 10 && day <= 23)) {
            strValue = "天秤座";
        } else if ((month == 10 && day > 23) || (month == 11 && day <= 22)) {
            strValue = "天蝎座";
        } else if ((month == 11 && day > 22) || (month == 12 && day <= 21)) {
            strValue = "射手座";
        } else if ((month == 12 && day > 21) || (month == 1 && day <= 19)) {
            strValue = "魔羯座";
        }


        return strValue;
    }


    /**
     * 根据身份证号，自动获取对应的生肖
     *
     * @param idCard 身份证号码
     * @return 生肖
     */
    public static String getZodiacById(String idCard) { // 根据身份证号，自动返回对应的生肖

        String sSX[] = {"猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗"};
        int year = getYearByIdCard(idCard);
        int end = 3;
        int x = (year - end) % 12;
        String retValue = "";
        retValue = sSX[x];
        return retValue;
    }


    /**
     * 根据身份证号，自动获取对应的天干地支
     *
     * @param idCard 身份证号码
     * @return 天干地支
     */
    public static String getChineseEraById(String idCard) { // 根据身份证号，自动返回对应的生肖

        String sTG[] = {"癸", "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "任"};
        String sDZ[] = {"亥", "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌"};
        int year = getYearByIdCard(idCard);
        int i = (year - 3) % 10;
        int j = (year - 3) % 12;
        String retValue = "";
        retValue = sTG[i] + sDZ[j];
        return retValue;
    }

    /**
     * 验证台湾身份证号码
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateTWCard(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
    }

    /**
     * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
     * <p>
     * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
     * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
     * </p>
     * <p>
     * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
     * </p>
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateHKCard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum = 0;
        if (card.length() == 9) {
            sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
                    + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0) ? true : false;
    }


    public static void main(String[] args) {
        System.out.println(getAgeByIdCard("23010219850927001X"));
        System.out.println(getBirthdayByIdCard("23010219850927001X"));
        System.out.println("15位" + isIDCardNo("340524800101001"));
        System.out.println("18位" + isIDCardNo("23010219850927001X"));

    }
}
