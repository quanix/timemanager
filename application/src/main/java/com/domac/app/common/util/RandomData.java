package com.domac.app.common.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Author : lihaoquan
 * Description :随机测试数据生成工具
 */
public class RandomData {

    private static Random random = new Random();

    /**
     * 生成随机ID
     * @return
     */
    public static String randomId() {
        return random.nextLong()+"";
    }

    /**
     * 生成随机的名字
     * @param prefix
     * @return
     */
    public static String randomName(String prefix) {
        return prefix + random.nextInt(10000);
    }


    /**
     * 从列表list中随机返回一个
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T randomOne(List<T> list) {
        Collections.shuffle(list);
        return list.get(0);
    }

    /**
     * 从列表中随机返回一个子列表
     * @param list
     * @param n
     * @param <T>
     * @return
     */
    public static <T> List<T> randomSome(List<T> list, int n) {
        Collections.shuffle(list);
        return list.subList(0,n);
    }

    /**
     * 从列表中随机返回一个子列表
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> randomSome(List<T> list) {
        int size = random.nextInt(list.size());
        if(size == 0) {
            size = 1;
        }
        return randomSome(list,size);
    }
}
