package com.example.shoppingmall.common.util;

import java.time.LocalDate;
import java.util.Random;

public class FormatUtil {
    private FormatUtil() {}

    // 오늘 날짜를 YYYYMMDD 포맷으로 반환
    public static String getTodayAsYYYYMMDD() {
        LocalDate today = LocalDate.now();
        return String.format("%04d%02d%02d", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
    }

    // 지정한 길이의 랜덤 숫자 문자열 반환
    public static String getRandomNumberString(int length) {
        int max = (int) Math.pow(10, length);
        return String.format("%0" + length + "d", new Random().nextInt(max));
    }
} 