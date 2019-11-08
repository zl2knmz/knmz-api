package com.knmz.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * CodeGenerator
 *
 * @Author: chenzeping
 * @Date: 2019/9/3 18:03
 */
public final class CodeGenerator {
    private static long start = 1514736000000L;
    private static double[] units = new double[]{1d, 10000d, 8640000d, 10000000d, 86400000d, 600000000d, 36000000000d, 86400000000d, 864000000000d};
    private static int[] unitsLength = new int[]{16, 12, 9, 9, 8, 7, 6, 5, 4};

    public static String generate(int randomLength, int baseCode) {
        return generate(randomLength, baseCode, 0, System.currentTimeMillis());
    }

    public static String generate(int randomLength, int baseCode, int snLength, long timestamp) {
        StringBuilder sb = new StringBuilder();
        sb.append(randomNumber(randomLength));
        int index = baseCode;
        long ticks = Double.valueOf((timestamp - start) * 10000).longValue();
        sb.append(StringUtils.leftPad(String.valueOf((new Double(ticks / units[index])).longValue()), unitsLength[index], '0'));
        sb.append(randomNumber(snLength));
        return sb.toString();
    }

    private static String randomNumber(int length) {
        return String.valueOf(Math.random()).substring(2, 2 + length);
    }

    public class BaseCode {
        /**
         * 16位可持續30年，超過30年自動變為17位
         */
        public static final int TICKS = 0;
        /**
         * 12位可持續30年，超過30年自動變為13位
         */
        public static final int MILLISECONDS = 1;
        /**
         * 9位可持續30年，超過30年自動變為10位
         */
        public static final int MS864 = 2;
        /**
         * 9位可持續30年，超過30年自動變為10位
         */
        public static final int SECONDS = 3;
        /**
         * 8位可持續30年，超過30年自動變為9位
         */
        public static final int S8P64 = 4;
        /**
         * 7位可持續30年，超過30年自動變為8位
         */
        public static final int MINUTES = 5;
        /**
         * 6位可持續100年，超過100年自動變為7位
         */
        public static final int HOURS = 6;
        /**
         * 5位可持續30年，超過30年自動變為6位
         */
        public static final int H2P4 = 7;
        /**
         * 4位可持續30年，超過30年自動變為5位
         */
        public static final int DAYS = 8;
    }
}
