package com.neau.crm.web.test;

import com.neau.crm.utils.DateTimeUtils;

import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
        //验证失效时间
        String expierTime = "2019-10-25 12:22:33";
        String currentTime = DateTimeUtils.getSysTime();
        int count = expierTime.compareTo(currentTime);
        System.out.println(count);
        //验证锁定状态
        String lockState = "1";
        if("0".equals(lockState)){
            System.out.println("账号已锁定");
        }else if("1".equals(lockState)){
            System.out.println("账号开启");
        }
        //验证ip锁定
        String ip = "192.168.133.2";
        String allowsIp = "192.168.133.2,192.168.122.5";
        if(allowsIp.contains(ip)){
            System.out.println("允许登录！");
        }else{
            System.out.println("ip受限!");
        }
    }
}
