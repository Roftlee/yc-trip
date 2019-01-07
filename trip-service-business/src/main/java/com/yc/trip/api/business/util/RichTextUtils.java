package com.yc.trip.api.business.util;

public class RichTextUtils {

    /**
     * 从HTML提取纯文本
     * @param strHtml
     * @return
     */
    public static String extractText(String strHtml) {
     String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签    
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符    
        return txtcontent;  
   }  
}
