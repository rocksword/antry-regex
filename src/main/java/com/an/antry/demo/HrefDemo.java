package com.an.antry.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrefDemo {

    public static void main(String[] args) {
        String input1 = "<a class=\"abc\" href=\"http://www.baidu.com\">";
        System.out.println("\n findUrl from " + input1);
        findUrl(input1);

        String input2 = "<a class=\"abc\" href=\"'http://proxyfreak.org/proxy/7928' target='_blank' style='color:'\">";
        System.out.println("\n findUrl from " + input2);
        findUrl(input2);

        String input3 = "<a class=\"abc\" href=\"'http://proxyfreak.org/proxy/7928' target='_blank' style='color:'\">";
        System.out.println("\n findUrl from " + input3);
        findUrl(input3);

        String input4 = "'http://proxyfreak.org/proxy/7928' target='_blank' style='color:";
        System.out.println("\n findFromHref from " + input4);
        findFromHref(input4);

        String input5 = "'http://proxyfreak.org/proxy/7928' target='_blank' style='color:";
        System.out.println("\n findHttp from " + input5);
        findHttp(input5);

        String input6 = "               <td align=\"center\">2013-08-17 19:45:00.0</td>";
        System.out.println("\n findTime from " + input6);
        findTime(input6);

        String input7 = "<td align=\"right\"><span style=\"padding-right:5px;\"><a href=\"soccer/team/25\" target=\"_blank\" title=\"\" class=\"a3\">利物浦</a> </span></td>";
        System.out.println("\n findTeam from " + input7);
        findTeam(input7);

        String input8 = "               2:2";
        System.out.println("\n findScore from " + input8);
        findScore(input8);

        String input9 = "<a href=\"http://fenxi.zgzcw.com/1765699/bjop\" target=\"_blank\" class=\"a_4\">欧</a>";
        System.out.println("\n findBjop from " + input9);
        findBjop(input9);

        String rawFilePath = "D:\\github\\lfs-parent\\raw\\eng\\2013\\1.txt";
        String mergeFilePath = rawFilePath.replaceAll("raw", "merge");
        System.out.println("mergeFilePath=" + mergeFilePath);

        String input10 = "<title>利物浦 VS 斯托克城_欧赔对比-中国足彩网</title>";
        System.out.println("\n findHostGuest from " + input10);
        findHostGuest(input10);

        String input11 = "      <td   data=\"1.33\"     class=\"#1 border-l      \"       > 1.33 </td>";
        System.out.println("\n findHostGuest from " + input11);
        findOdds(input11);

        String input12 = "<a href=\"soccer/team/299\" target=\"_blank\" title=\"\" class=\"a3\">竞技</a>";
        System.out.println("\n findHostGuest from " + input12);
        findTeamIdName(input12);
    }

    private static void findTeamIdName(String input) {
        String pattern = ".*soccer/team/(\\d+).*>\\s*(.*)</a>";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("result=[" + m.group(1).trim() + "],[" + m.group(2).trim() + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findOdds(String input) {
        String pattern = ".*>\\s*(\\d*.\\d*)\\s*</td>.*";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("result=[" + m.group(1) + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findHostGuest(String input) {
        String pattern = "\\s*<title>\\s*(.*)\\s*VS\\s*(.*)\\s*_.*</title>\\s*";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("result=[" + m.group(1) + "] - [" + m.group(2) + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findBjop(String input) {
        String pattern = ".*http://fenxi.zgzcw.com/(\\d+)/bjop.*";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("result=[" + m.group(1) + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findScore(String input8) {
        if (input8.matches("\\s*\\d+:\\d+\\s*")) {
            System.out.println("result=[" + input8.trim() + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findTeam(String input) {
        String pattern = ".*soccer/team/(\\d+).*";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("result=[" + m.group(1) + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findTime(String input) {
        String pattern = ">\\s*(\\d+-\\d+-\\d+\\s\\d+:\\d+:\\d+.*)\\s*<";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("result=[" + m.group(1) + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findUrl(String input) {
        String pattern = "href=\"([^\"]*)\"";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        processFindHref(m);
    }

    private static void findFromHref(String input) {
        String pattern = "'(http:.*)'.*target.*";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        processFindHref(m);
    }

    private static void processFindHref(Matcher m) {
        if (m.find()) {
            String href = m.group(1);
            System.out.println("href= [" + href + "]");
            int s = href.indexOf("http");
            if (s == -1) {
                return;
            }
            int e = href.indexOf(" ");
            String url = null;
            if (e != -1) {
                url = href.substring(s, e - 1).trim();
            } else {
                url = href.substring(s).trim();
            }
            System.out.println("URL= [" + url + "]");
        } else {
            System.out.println("Not found.");
        }
    }

    private static void findHttp(String input) {
        String pattern = "http://(.*)";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        if (m.find()) {
            System.out.println("url=[" + m.group(1) + "]");
        } else {
            System.out.println("Not found.");
        }

        String hrefStr = "url=http://ab.coma,";
        int s = hrefStr.indexOf("url=http");
        int e = hrefStr.indexOf(",");
        if (s != -1 && e != -1 && e > s) {
            hrefStr = hrefStr.substring(s + 4, e);
        }
        System.out.println(hrefStr);
    }
}
