package com.yc.trip.api.core.util;

import org.apache.commons.lang3.StringUtils;
import org.go.framework.util.common.StringUtil;

import java.util.regex.Pattern;

/**
 * Title: RegexUtils
 * Description: 正则表达式工具类
 * Company: gigold
 * @author liubao
 * @date 2016年10月19日下午4:57:07
 */
public class RegexUtils {

    /**
     * 大于零整数
     */
    public static final String REGEX_GREATER_ZERO_INT = "^\\+?[1-9][0-9]*$";
    /**
     * 定义script的正则表达式
     */
    public static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    public static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    public static final String REGEX_HTML = "<[^>]+>";

    /**
     * Title: verifyPara Description:正则匹配参数
     * @param str   参数
     * @param regex 正则表达式
     * @return
     * @author liubao
     * @date 2016年10月9日上午9:53:41
     */
    public static boolean verifyPara(String str, String regex) {

        if(StringUtils.isBlank(str) || StringUtils.isBlank(regex)) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    /**
     * 过滤Html
     * @param html
     * @return
     */
    public static String filterHtml(String html) {
        if(StringUtil.isBlank(html)) {
            return html;
        }
        // 过滤script标签
        html = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        // 过滤style标签
        html = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        // 过滤html标签
        html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE).matcher(html).replaceAll("");
        // 返回文本字符串
        return html.trim();
    }
}
