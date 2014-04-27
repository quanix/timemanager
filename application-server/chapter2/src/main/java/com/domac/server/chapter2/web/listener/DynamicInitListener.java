package com.domac.server.chapter2.web.listener;

import com.domac.server.chapter2.web.filter.DynamicFilter;
import com.domac.server.chapter2.web.servlet.DynamicServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * @author : lihaoquan
 */
@WebListener
public class DynamicInitListener implements ServletContextListener {


    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        servletContext.addFilter("dynamicFilter",DynamicFilter.class);

        ServletRegistration.Dynamic dynamic1
                = servletContext.addServlet("dynamicServlet1", DynamicServlet.class);
        dynamic1.setLoadOnStartup(1);
        dynamic1.addMapping("/dynamic1");

        ServletRegistration.Dynamic dynamic2
                = servletContext.addServlet("dynamicServlet2",
                "com.domac.server.chapter2.web.servlet.DynamicServlet");
        dynamic2.addMapping("/dynamic2");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
