package com.neau.crm.web.controller;

import com.neau.crm.exceptions.LoginException;
import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.MD5Utils;
import com.neau.crm.utils.PrintJson;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.service.UserService;
import com.neau.crm.web.service.serviceImpl.UserServiceImpl;
import org.apache.catalina.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends HttpServlet {
    private UserService usrManager;
    private UserService usrSearcher;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        usrManager = context.getBean("userService",UserServiceImpl.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse rep){
        System.out.println("用户进入控制器");//测试用
        String path = req.getServletPath();
        switch (path){
            case "/users/login.do":
                login(req,rep);
                break;
            case "/users/getUserList.do":
                selectAllUser(req,rep);
                break;
            default:
                System.out.println("未定义的URL："+path);
        }
    }
    private void login(HttpServletRequest req,HttpServletResponse rep){
        System.out.println("查找登录用户");
        String loginAct = req.getParameter("loginAct");
        String loginPwd = req.getParameter("loginPwd");
        //明文转换成密文
        loginPwd = MD5Utils.getMD5Str(loginPwd);
        //接收IP地址
        String ip = req.getRemoteAddr();
        //创建service对象 spring创建代理
        //us = new UserServiceImpl();//需改为代理类
        //提取User对象，并放入Session域
        try {
            SysUser user = usrManager.login(loginAct, loginPwd, ip);
            req.getSession().setAttribute("user", user);
            String json = "{\"success\":true}";
            rep.getWriter().print(json);
            //System.out.println(user.getUname());
        }catch (LoginException  e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(rep,map);
        }catch (IOException ie){
            ie.printStackTrace();
        }
    }
    private void selectAllUser(HttpServletRequest req,HttpServletResponse rep){
        System.out.println("查找所有用户");
        String UUID = ((SysUser)req.getSession().getAttribute("user")).getId();//以后涉及权限的时候有用
        List<SysUser> users = usrSearcher.selectAllUser();
        PrintJson.printJsonObj(rep,users);
    }
}
