/**
 * gigold Inc.  吉高支付 湖南宝伦天地信息科技有限公司
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.yc.trip.api.core.enums;

/**
 * 公共Regex
 * 
 * @author dawei.liu
 * @version $Id: CommonRegex.java,v 0.1 2016年11月3日 下午4:03:15 xxx$Exp
 */
public enum CommonRegex {

    /**
     * IP地址
     */
    IP("IP","^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$"),
    
    /**
     * 端口
     */
    PORT("PORT","^([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$"),

    /**
     * 手机号码
     */
    MOBILE("MOBILE","^1\\d{10}$"),
    
    /**
     * 邮箱地址
     */
    EMAIL("EMAIL","^\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?$"),
    
    /**
     * 正整数
     */
    NEGATIVE_DIGIT("正整数","^[1-9]\\d*|0$"),

    /**
     * 整数
     */
    DIGIT("整数","^\\-?[1-9]\\d+$"),
    
    /**
     * 中文
     */
    CHINESE("中文","^[\u4E00-\u9FA5]+$")
   ;    
    private String name;
    
    private String regex;
    
    CommonRegex(String name, String regex){
        this.name = name;
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
