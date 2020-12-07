package com.neau.crm.web.controller;

import com.neau.crm.utils.DateTimeUtils;
import com.neau.crm.utils.PrintJson;
import com.neau.crm.utils.UUIDUtils;
import com.neau.crm.web.domain.Activity;
import com.neau.crm.web.domain.ActivityRemark;
import com.neau.crm.web.domain.SysUser;
import com.neau.crm.web.domain.vo.PageInfo;
import com.neau.crm.web.service.ActivityService;
import com.neau.crm.web.service.UserService;
import com.neau.crm.web.service.serviceImpl.*;
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

public class ActivityController extends HttpServlet {
    private ActivityService asManager;
    private ActivityService asSearcher;
    private UserService usrSearcher;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        asManager = context.getBean("activityManager", ActivityManageService.class);
        asSearcher = context.getBean("activitySearcher", ActivitySearchService.class);
        usrSearcher = context.getBean("userSearcher", UserSearchService.class);
    }

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
            case "/functions/activity/selectUserListAndActivity.do":
                selectUserListAndActivity(request,response);
                break;
            case "/functions/activity/update.do":
                updateActivity(request,response);
                break;
            case "/functions/activity/detail.do":
                showDetail(request,response);
                break;
            case "/functions/activity/getRemarkListByAid.do":
                getRemarkListByAid(request,response);
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
        boolean flag = asManager.save(ac);
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
        PageInfo<Activity> vo = asManager.pageList(parameter);
        PrintJson.printJsonObj(rep,vo);
    }
    private void deleteByIds(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进行删除市场活动操作");
        String ids[] = request.getParameterValues("id");
        boolean flag = asManager.deleteByIds(ids,((SysUser)request.getSession().getAttribute("user")).getId());
        PrintJson.printJsonFlag(response,flag);
    }
    private void showDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入展示市场活动详情页面");
        String id = request.getParameter("id");
        Activity a = asSearcher.getDetailById(id);
        //拿到a中的createBy、editBy和acowner，查找对应的
        Map<String,String> propertiesMap = new HashMap<String,String>();
        propertiesMap.put("acowner",a.getAcowner());
        propertiesMap.put("createBy",a.getCreateBy());
        propertiesMap.put("editBy",a.getEditBy());
        Map<String,String> resultMap = usrSearcher.getNameMap(propertiesMap);
        a.setAcowner(resultMap.get("acowner"));
        a.setCreateBy(resultMap.get("createBy"));
        a.setEditBy(resultMap.get("editBy"));
        //System.out.println(a.getAcowner()+""+a.getCreateBy()+""+a.getEditBy());
        request.getSession().setAttribute("a",a);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }
    private void getRemarkListByAid(HttpServletRequest request,HttpServletResponse response){
        System.out.println("进入市场活动备注查找方法");
        String id = request.getParameter("activityId");
        List<ActivityRemark> arList = asSearcher.getRemarkListByAid(id);
        PrintJson.printJsonObj(response,arList);
    }
    private void selectUserListAndActivity(HttpServletRequest request,HttpServletResponse response){
        System.out.println("为修改操作查询用户列表和对应活动");
        String id = request.getParameter("id");
        List<SysUser> usrList = usrSearcher.selectAllUser();
        Activity a = asSearcher.getDetailById(id);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("uList",usrList);
        map.put("a",a);
        PrintJson.printJsonObj(response,map);
    }
    private void updateActivity(HttpServletRequest request,HttpServletResponse response){
        System.out.println("更新市场活动");
        Activity a = new Activity();
        a.setId(request.getParameter("id"));
        a.setAcname(request.getParameter("acname"));
        a.setAcowner(request.getParameter("acowner"));
        a.setStartDate(request.getParameter("startDate"));
        a.setEndDate(request.getParameter("endDate"));
        a.setCost(request.getParameter("cost"));
        a.setDescriptions(request.getParameter("descriptions"));
        a.setEditBy(((SysUser)request.getSession().getAttribute("user")).getId());
        a.setEditTime(DateTimeUtils.getSysTime());
        boolean flag = asManager.updateActivity(a);
        PrintJson.printJsonFlag(response,flag);
    }
}
