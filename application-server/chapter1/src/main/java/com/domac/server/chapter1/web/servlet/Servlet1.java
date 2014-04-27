package com.domac.server.chapter1.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : lihaoquan
 */
@WebServlet(name = "/servlet1",urlPatterns = {"/s1","/s1/*"},loadOnStartup = 1,
           initParams = {
                   @WebInitParam(name = "msg",value = "Hello My First Servlet3 Demo")
           })
public class Servlet1 extends HttpServlet {

    private String msg;

    @Override
    public void init() throws ServletException {
        super.init();
        msg = this.getInitParameter("msg");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        System.out.println("===="+msg);
    }
}
