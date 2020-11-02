package com.neau.crm.web.controller.filter;

import com.neau.crm.web.domain.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HasLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        String path = request.getServletPath();
        if("/users/login.do".equals(path) || "/login.jsp".equals(path) ){
            filterChain.doFilter(req,rep);
        }else {
            System.out.println("进行登录验证");
            HttpSession session = request.getSession();
            SysUser user = (SysUser) session.getAttribute("user");
            if (user != null) {
                filterChain.doFilter(req, rep);
            } else {
                response.sendRedirect(request.getContextPath() + "/index.html");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
