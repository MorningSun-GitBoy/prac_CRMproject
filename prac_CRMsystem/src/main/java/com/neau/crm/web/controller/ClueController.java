package com.neau.crm.web.controller;

import com.neau.crm.web.service.ClueService;
import com.neau.crm.web.service.serviceImpl.ClueManageService;
import com.neau.crm.web.service.serviceImpl.ClueSearchService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClueController extends HttpServlet {
    ClueService clueManager;
    ClueService clueSearcher;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/functions/clue/pageList.do":
                pageList(req,resp);
                break;
            default:
                System.out.println("出现了一个奇怪的问题");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        clueManager = context.getBean("clueManager", ClueManageService.class);
        clueSearcher = context.getBean("clueSearcher", ClueSearchService.class);
    }
    private void pageList(HttpServletRequest request,HttpServletResponse response){

    }
}
