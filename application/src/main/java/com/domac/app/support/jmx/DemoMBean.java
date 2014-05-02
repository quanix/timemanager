package com.domac.app.support.jmx;

/**
 * @author : lihaoquan
 */
public interface DemoMBean {

    public void sayHello();

    public int add(int x, int y);

    public String getName();

    public int getCacheSize();

    public void setCacheSize(int size);
}
