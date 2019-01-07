package com.yc.trip.api.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: SmsCodeUtils
 * Description: 手机号验证--工具类
 * Company: gigold
 * @author chenqh
 * @date 2016年8月5日下午5:28:43
 */
public class SmsCodeUtils {

	/**
	 * Title: isPhoneNumber
	 * Description: 这个方法判断是不是合法的手机号码 
	 * @author chenqh
	 * @date 2016年8月5日下午5:44:48
	 * @param phoneNumber
	 * @return
	 */
    public static boolean isPhoneNumber(String phoneNumber){
        //手机号码长度
        int phoneLength=phoneNumber.length();
        //第一位是不是0
        String phoneOne=phoneNumber.substring(0,1);
       
        //是纯数字  并且长度等于11   并且第一位不是0   
        return  isNumeric(phoneNumber)&&phoneLength==11&&!phoneOne.equals("0");
    }

   
    /**
     * Title: isNumeric
     * Description: 这个方法判断字符串是不是纯数字
     * @author chenqh
     * @date 2016年8月5日下午5:45:03
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
