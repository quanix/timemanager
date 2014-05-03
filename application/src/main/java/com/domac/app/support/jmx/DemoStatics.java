package com.domac.app.support.jmx;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author : lihaoquan
 */
public class DemoStatics {

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName objectName = new ObjectName("Myapp:name=Demo");
        mBeanServer.registerMBean(new Demo(),objectName);

        // Wait forever
        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);


    }
}
