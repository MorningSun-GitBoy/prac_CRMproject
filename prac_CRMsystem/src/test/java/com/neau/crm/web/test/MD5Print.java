package com.neau.crm.web.test;

import com.neau.crm.utils.MD5Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5Print {
    /**
     * 用于准备待验证的密码数据
     */
    public static void main(String[] args) {
        String pwdStr;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while(flag){
            pwdStr = sc.nextLine();
            flag = !"over".equals(pwdStr);
            pwdStr = getMD5Str(pwdStr);
            System.out.println(pwdStr==null?"WRONG!!":pwdStr);
        }
    }
    public static String getMD5Str(String word){
        int number;
        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            byte results[] = md.digest(word.getBytes());
            StringBuffer sb = new StringBuffer();
            for(byte b : results){
                number = b & 0xff;
                String str = Integer.toHexString(number);
                if(1 == str.length()){
                    sb.append(0);
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
