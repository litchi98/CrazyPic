package com.qq.crazypic.utilities;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    private DateUtil() {
    }

    public static Date parse(@Nullable String pattern, String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        simpleDateFormat.applyPattern(TextUtils.isEmpty(pattern) ? DEFAULT_DATE_FORMAT_PATTERN : pattern);
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }
}
