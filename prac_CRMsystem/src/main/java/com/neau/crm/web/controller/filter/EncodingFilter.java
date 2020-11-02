package com.neau.crm.web.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入过滤字符编码的过滤器");
        req.setCharacterEncoding("UTF-8");
        rep.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(req,rep);
    }

    @Override
    public void destroy() {

    }
}
