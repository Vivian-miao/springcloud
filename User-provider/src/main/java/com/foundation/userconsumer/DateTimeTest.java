package com.foundation.userconsumer;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;

import java.util.Date;

/**
 * 2021-07-09 09:23:51
 *
 * @author zhh
 */
public class DateTimeTest {
    public static void main(String[] args) {
        // 判断星座
        String constellationNameCn = DateTimeCalculatorUtil.getConstellationNameCn("09-10");
        System.out.println("constellationNameCn = " + constellationNameCn);

        // 判断是否到生日
        boolean birthDay = DateTimeCalculatorUtil.isBirthDay(new Date());
        System.out.println("birthDay = " + birthDay);

        // 中国工作日计算
        boolean chineseWorkDay = DateTimeCalculatorUtil.isChineseWorkDay(new Date(), "2021-01-01:0");
        System.out.println("chineseWorkDay = " + chineseWorkDay);

    }
}
