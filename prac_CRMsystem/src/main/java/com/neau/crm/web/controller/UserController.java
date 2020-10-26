package com.neau.crm.web.controller;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.MD5Utils;
import com.neau.crm.utils.PrintJson;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.service.UserService;
import com.neau.crm.web.service.serviceImpl.UserServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse rep){
        System.out.println("用户进入控制器");//测试用
        String path = req.getServletPath();
        if("/users/login.do".equals(path)){
            login(req,rep);
        }else if("".equals(path)){

        }
    }
    private void login(HttpServletRequest req,HttpServletResponse rep){
        String loginAct = req.getParameter("loginAct");
        String loginPwd = req.getParameter("loginPwd");
        //明文转换成密文
        loginPwd = MD5Utils.getMD5Str(loginPwd);
        //接收IP地址
        String ip = req.getRemoteAddr();
        //创建service对象
        UserService us = new UserServiceImpl();//需改为代理类
        //提取User对象，并放入Session域
        try {
            SysUser user = us.login(loginAct, loginPwd, ip);
            req.getSession().setAttribute("user", user);
            String json = "{\"success\":true}";
            rep.getWriter().print(json);
        }catch (LoginException  e){
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(rep,map);
        }catch (IOException ie){
            ie.printStackTrace();
        }
    }
}
