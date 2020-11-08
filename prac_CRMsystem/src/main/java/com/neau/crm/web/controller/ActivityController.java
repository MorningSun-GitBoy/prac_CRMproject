package com.neau.crm.web.controller;

import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.UUIDUtils;
import com.neau.crm.web.domain.SysUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("进入到市场活动控制器");
        String path = request.getServletPath();
        switch (path){
            case "/functions/activity/getUserList.do" :
                request.getRequestDispatcher("/users/getUserList.do").forward(request,response);
                break;
            case "/functions/activity/save.do":
                save(request,response);
                break;
            default:
                System.out.println("出现一个奇怪的问题");
        }
    }
    private void save(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进行市场活动添加操作");
        String id = UUIDUtils.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtils.getSysTime();
        String createBy = ((SysUser)request.getSession().getAttribute("user")).getId();
    }
}