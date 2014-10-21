package com.an.antry.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    private static String REGEX = "a*b";
    private static String INPUT = "aabfooaabfooabfoob";
    private static String REPLACE = "-";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获得匹配器对象
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            System.out.println(sb.toString());
            m.appendReplacement(sb, REPLACE);
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
