package com.domac.app.support.jmx;

/**
 * @author : lihaoquan
 */
public class JmxTestBean implements IJmxTestBean {

    private String name;
    private int age;
    private boolean isSuperman;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int add(int x, int y) {
        return x+y;
    }

    @Override
    public void dontExposeMe() {
        throw new RuntimeException();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
