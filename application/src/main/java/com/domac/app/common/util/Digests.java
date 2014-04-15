package com.domac.app.common.util;

import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Author : lihaoquan
 * Description : 支持SHA-1/MD5消息摘要的工具类.
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 *
 * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
 * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
 */
public class Digests {

    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";

    private static SecureRandom secureRandom = new SecureRandom();


    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    /******************************   下面的是针对字符串进行的消息摘要处理    *****************************/

    /**
     * 对输入字符串进行sha1散列.
     */
    public static byte[] sha1(byte[] input) {
        return digest(input, SHA1, null, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, SHA1, salt, 1);
    }

    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
     private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
         try {

             MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
             if(salt!=null) {
                 messageDigest.update(salt);
             }
             byte[] result = messageDigest.digest(input);
             for(int i = 1; i< iterations ; i++) {
                 messageDigest.reset();
                 result = messageDigest.digest(input);
             }
             return result;
         }catch (GeneralSecurityException e) {
             throw Exceptions.unchecked(e);
         }
     }


    /******************************   下面的是针对文件进行的消息摘要处理    *****************************/

    /**
     * 对文件进行md5散列.
     */
    public static byte[] md5(InputStream input) throws IOException {
        return digest(input, MD5);
    }

    /**
     * 对文件进行sha1散列.
     */
    public static byte[] sha1(InputStream input) throws IOException {
        return digest(input, SHA1);
    }

    /**
     * 对输入流进行信息摘要处理
     * @param input
     * @param algorithm
     * @return
     * @throws IOException
     */
    private static byte[] digest(InputStream input,String algorithm) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8*1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer,0,bufferLength);
            while(read > -1) {
                messageDigest.update(buffer,0,read);
                read = input.read(buffer,0,bufferLength);
            }
            return messageDigest.digest();
        }catch (Exception e) {
           throw Exceptions.unchecked(e);
        }
    }

}
