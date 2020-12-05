package com.neau.crm.web.test;

import java.util.HashMap;
import java.util.Map;

public class CastTest {
    public static void main(String[] args) {
        Map<String,String> newMap = new HashMap<String,String>();
        newMap.put("hhhhh","jjjjjj");
        Object[] objList = newMap.keySet().toArray();
        System.out.println(objList[0]);
        System.out.println(objList[0].getClass());
        String[] strList = (String[]) objList;
        System.out.println(strList[0]);
    }
}
