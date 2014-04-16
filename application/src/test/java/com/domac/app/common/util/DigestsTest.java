package com.domac.app.common.util;

import org.junit.Test;

/**
 * @author : lihaoquan
 *
 * 支持SHA-1/MD5消息摘要的工具测试类
 */
public class DigestsTest {


    @Test
    public void testdigest() {

        byte[] salt = Digests.generateSalt(8);
        String sa = Encodes.encodeHex(salt);

        System.out.println("salt = "+sa);

        String input = "hello123456";

        byte[] result =  Digests.sha1(input.getBytes(),salt,10);

        String hex = Encodes.encodeHex(result);
        String base64 = Encodes.encodeBase64(result);

        System.out.println("Hex = "+hex);
        System.out.println("base64 = "+base64);

    }
}
