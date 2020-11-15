package com.neau.crm.web.controller;

import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.PrintJson;
import com.neau.crm.utils.UUIDUtils;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;
import com.neau.crm.web.service.serviceImpl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActivityController extends HttpServlet {
    ActivityService as = new ActivityServiceImpl();
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
            case "/functions/activity/pageList.do":
                searchActivity(request,response);
                break;
            case "/functions/activity/delete.do":
                deleteByIds(request,response);
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
        Activity ac = new Activity();
        ac.setId(id);
        ac.setAcname(name);
        ac.setAcowner(owner);
        ac.setStartDate(startDate);
        ac.setEndDate(endDate);
        ac.setCost(cost);
        ac.setDescriptions(description);
        ac.setCreateTime(createTime);
        ac.setCreateBy(createBy);
        boolean flag = as.save(ac);
        //System.out.println(flag);
        PrintJson.printJsonFlag(response,flag);
    }
    private void searchActivity(HttpServletRequest req,HttpServletResponse rep){
        System.out.println("查询市场活动信息列表");
        String name = req.getParameter("name");
        String owner = req.getParameter("owner");
        String startDate = req.getParameter("startDate");
        String endDate =  req.getParameter("endDate");
        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");//每页展现记录数
        /*
         *   sql:SELECT * FROM table_name LIMIT 略过记录数,每页记录数
         *   每页记录数：pageSize
         *   略过记录数：pageSize*pageNo
         */
        StringBuilder sb = new StringBuilder();
        Integer skipNum =(Integer.valueOf(pageNo)-1)*Integer.valueOf(pageSize);
        Map<String,Object> parameter = new HashMap<String,Object>();
        parameter.put("actName",name);
        parameter.put("owner",owner);
        parameter.put("startDate",startDate);
        parameter.put("endDate",endDate);
        parameter.put("skipNum",skipNum);
        parameter.put("pageSize",Integer.valueOf(pageSize));
        PageInfo<Activity> vo = as.pageList(parameter);
        PrintJson.printJsonObj(rep,vo);
    }
    private void deleteByIds(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进行删除市场活动操作");
        String ids[] = request.getParameterValues("id");
        boolean flag = as.deleteByIds(ids);
        PrintJson.printJsonFlag(response,flag);
    }
}
