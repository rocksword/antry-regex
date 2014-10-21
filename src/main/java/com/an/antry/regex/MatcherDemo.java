package com.an.antry.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {
    private static final String REGEX = "\\bdog\\b";
    private static final String INPUT = "dog dog dog doggie dogg";

    public static void main(String[] args) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获得一个匹配器对象
        int count = 0;
        while (m.find()) {
            count++;
            System.out.println("Match number " + count + ", start(): " + m.start() + ", end(): " + m.end());
        }
    }
}