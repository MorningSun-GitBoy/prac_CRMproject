package com.neau.crm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String getMD5Str(String word){
        int number;
        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] results = md.digest(word.getBytes());
            StringBuffer bf = new StringBuffer();
            for(byte b : results){
                number = b & 0xff;
                String str = Integer.toHexString(number);
                if(1 == str.length()){
                    bf.append("0");
                }
                bf.append(str);
            }
            return bf.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
