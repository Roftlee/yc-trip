package com.yc.trip.api.core.util;


/**
 * Desc: 营业执照编号 验证
 * Created  2016/5/14.
 */
public class BusinessLicenseUtils {

    /**
     * 营业执照注册号校验正确返回码
     */
    public static String isBusinesslicense = "true";
    private static String error_Businesslicense_Empty = "请输入营业执照注册号";
    public static String error_Businesslicense = "您输入的营业执照注册号有误，请核对后再输!";
    public static String error_Businesslicense_No = "您输入的营业执照注册号不足15位，请核对后再输!";

    static String  test = "110108000000016" ;// 营业执照号

    public static void main(String[] args ){
        System.out.println(isBusinesslicense(test));
        
    }

    /**
     * 校验 营业执照注册号
     * @param businesslicense
     * @return
     */
    public static boolean isBusinesslicense(String businesslicense){

         if(businesslicense.length()!=15){ 
            return false;
        }
        String businesslicensePrex14 = businesslicense.substring(0,14);// 获取营业执照注册号前14位数字用来计算校验码
        String businesslicense15 = businesslicense.substring(14,businesslicense.length());// 获取营业执照号的校验码
        char[] chars = businesslicensePrex14.toCharArray();
        int[] ints = new int[chars.length];
        for(int i=0; i<chars.length;i++){
            ints[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        getCheckCode(ints);
        if(businesslicense15.equals(getCheckCode(ints)+"")){// 比较 填写的营业执照注册号的校验码和计算的校验码是否一致
        
            return  true;
        }
        
        return false;
    }

    /**
     * 获取 营业执照注册号的校验码
     * @param ints
     * @return
     */
    private static int  getCheckCode(int[] ints){
        if (null != ints && ints.length > 1) {
            int ti = 0;
            int si = 0;// pi|11+ti
            int cj = 0;// （si||10==0？10：si||10）*2
            int pj = 10;// pj=cj|11==0?10:cj|11
            for (int i=0;i<ints.length;i++) {
                ti = ints[i];
                pj = (cj % 11) == 0 ? 10 : (cj % 11);
                si = pj + ti;
                cj = (0 == si % 10 ? 10 : si % 10) * 2;
                if (i == ints.length-1) {
                    pj = (cj % 11) == 0 ? 10 : (cj % 11);
                    return pj == 1 ? 1 : 11 - pj;
                }
            }
        }
        return -1;

    }

}
