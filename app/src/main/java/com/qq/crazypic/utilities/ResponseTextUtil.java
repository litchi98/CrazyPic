package com.qq.crazypic.utilities;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseTextUtil {

    private ResponseTextUtil() {

    }

    @Nullable
    public static List<String> parseImgUrl(String input) {
        if (TextUtils.isEmpty(input)) {
            return null;
        }
        Pattern compile = Pattern.compile("http[s]?://[\\w/.-]+");
        Matcher matcher = compile.matcher(input);
        ArrayList<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    @Nullable
    public static String unescapeHtmlText(String input) {
        return StringEscapeUtils.unescapeHtml4(input);
    }
}