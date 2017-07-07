package com.qbao.pay.notify.until;

import java.io.UnsupportedEncodingException;  

import org.apache.commons.lang3.StringUtils;
  
public class StringUtil {  
      
    /** 
     * 判断字符串是否为空 
     * @param str 
     * null、“ ”、“null”都返回true 
     * @return 
     */  
    public static boolean isNullString(String str) {  
        return (null == str || StringUtils.isBlank(str.trim()) || "null".equals(str.trim().toLowerCase())) ? true : false;  
    }  
      
    /** 
     * 格式化字符串 
     * 如果为空，返回“” 
     * @param str 
     * @return 
     */  
    public static String formatString(String str) {  
        if(isNullString(str)) {  
            return "";  
        } else {  
            return str;  
        }  
    }  
      
    /** 
     * 截取字符串，字母、汉字都可以，汉字不会截取半 
     * @param str 字符串 
     * @param n 截取的长度，字母数，如果为汉字，一个汉字等于两个字母数 
     * @return 
     */  
    public static String subStringByByte(String str, int n){  
        int num = 0;  
        try {  
            byte[] buf = str.getBytes("GBK");  
            if(n>=buf.length){  
                return str;  
            }  
            boolean bChineseFirstHalf = false;  
            for(int i=0;i<n;i++)  
            {  
                if(buf[i]<0 && !bChineseFirstHalf){  
                    bChineseFirstHalf = true;  
                }else{  
                    num++;  
                    bChineseFirstHalf = false;                
                }  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return str.substring(0,num);  
    }  
}  
