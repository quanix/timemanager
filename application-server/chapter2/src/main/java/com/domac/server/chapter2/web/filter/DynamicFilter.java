package com.domac.server.chapter2.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author : lihaoquan
 */
public class DynamicFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
           throws IOException, ServletException {
        System.out.println("Dynamic Filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
