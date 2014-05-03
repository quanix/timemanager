package com.domac.app.support.jmx;

/**
 * @author : lihaoquan
 */
public interface IJmxTestBean {

    public int getAge();

    public void setAge(int age);

    public String getName();

    public void setName(String name);

    public int add(int x, int y);

    public void dontExposeMe();

}
