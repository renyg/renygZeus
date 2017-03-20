package com.shining3d.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by fe on 2017/1/16.
 */
public class DecimalHelper {

    public static final String DEFAULT_FORMAT_PATTERN = "#.00";

    public static String formatNumber(BigDecimal number) {
        return formatNumber(number,DEFAULT_FORMAT_PATTERN);
    }

    public static String formatNumber(BigDecimal number,String format) {
        if (number != null) {
            DecimalFormat df = new DecimalFormat(format);
            return df.format(number);
        } else {
            return null;
        }
    }
}
