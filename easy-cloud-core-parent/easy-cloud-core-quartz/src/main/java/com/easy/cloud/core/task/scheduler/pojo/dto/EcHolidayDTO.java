package com.easy.cloud.core.task.scheduler.pojo.dto;

/**
 * @author daiqi
 * @create 2018-06-20 19:39
 */
public class EcHolidayDTO {
    private static final int TEN = 10;
    private Integer year;
    private Integer month;
    private Integer day;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");
        stringBuilder.append(year).append("-");
        if (month < TEN) {
            stringBuilder.append("0");
        }
        stringBuilder.append(month).append("-");
        if (day < TEN) {
            stringBuilder.append("0");
        }
        stringBuilder.append(day);
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }
}
