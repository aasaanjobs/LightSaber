package com.aasaanjobs.lightsaber.utils;

import java.text.DecimalFormat;

/**
 * Created by nazmuddinmavliwala on 24/05/16.
 */
public class StringUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static boolean equals(String value1, String value2) {
        return !isEmpty(value1) && !isEmpty(value2) && value1.equals(value2);
    }

    public static boolean equalsIgnoreCase(String value1, String value2) {
        return !isEmpty(value1) && !isEmpty(value2) && value1.equalsIgnoreCase(value2);
    }

    public static int extractInt(String str){
        String numberOnly= str.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberOnly);
    }
    public static String extractIntStr(String str){
        return str.replaceAll("[^0-9]", "");
    }


    public static String getFormattedSalary(Integer integer) {
        return new DecimalFormat("##,##,###").format(integer);
    }
}
