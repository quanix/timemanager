package com.domac.app.jetty;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.List;

/**
 * @author : lihaoquan
 */
public class JettyFactory {

    private static final String WEB_PATH = "application/webapp";
    private static final String WEBDEFAULT_PATH = "application/webapp/WEB-INF/classes/jetty/webdefault-windows.xml";

    /**
     * 创建Jetty的Server
     * @param port
     * @param contextPath
     * @return
     */
    public static Server createServer(int port,String contextPath) {

        Server server = new Server();
        server.setStopAtShutdown(true);//应用关闭的时候，也把JVM线程杀掉!!!

        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        connector.setReuseAddress(false);

        server.setConnectors(new Connector[]{connector});
        WebAppContext webAppContext = new WebAppContext(WEB_PATH,contextPath);
        //防止Lock住静态文件的问题.
        webAppContext.setDefaultsDescriptor(WEBDEFAULT_PATH);
        //进入handler的装配（Jetty是基于Handler的体系结构）
        server.setHandler(webAppContext);
        return server;
    }


    /**
     * 设置相关Jar包的名称
     */
    public static void setTldNames(Server server,String... jarNames) {

        WebAppContext context = (WebAppContext) server.getHandler();
        List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
        for (String jarName : jarNames) {
            jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
        }
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                StringUtils.join(jarNameExprssions, '|'));

    }


    /**
     * 用做重启applicationContext
     */
    public static void reloadContext(Server server) throws Exception {
        //Jetty是基于Handler的体系结构,这里获取对应的handler
        WebAppContext context = (WebAppContext) server.getHandler();
        context.stop();
        //修改相关的Jetty的类加载器
        WebAppClassLoader classLoader = new WebAppClassLoader(context);
        classLoader.addClassPath("webapp/WEB-INF/classes");//设置编译路径
        context.setClassLoader(classLoader);
        context.start();
        System.out.println("restart success!!");
    }

}
