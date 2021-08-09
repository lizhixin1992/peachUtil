package com.lzx.peachutil.utils.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author lizhixin
 * @date 2021/8/9 17:39
 */
public class MD5Util {

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final ThreadLocal<MessageDigest> threadLocal = new ThreadLocal<MessageDigest>();

    private static MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            throw nsaex;
        }
    }

    /**
     * 查询文件md5字符串
     *
     * @author     lizhixin
     * @date       2021/8/9 17:40
     */
    public static String getMD5String(File file) throws NoSuchAlgorithmException, IOException {
        return bufferToHex(getMD5Byte(file));
    }

    /**
     * 查询字符串md5字符串
     *
     * @author     lizhixin
     * @date       2021/8/9 17:40
     */
    public static String getMD5String(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return bufferToHex(getMD5Byte(str));
    }

    /**
     * 查询byte数组md5字符串
     *
     * @author     lizhixin
     * @date       2021/8/9 17:40
     */
    public static String getMD5String(byte[] bs) throws NoSuchAlgorithmException {
        return bufferToHex(getMD5Byte(bs));
    }

    public static String getMD5String(InputStream in) throws IOException, NoSuchAlgorithmException {
        return bufferToHex(getMD5New(in));
    }

    /**
     * 查询文件md5
     *
     * @author     lizhixin
     * @date       2021/8/9 17:40
     */
    public static byte[] getMD5Byte(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest messageDigest = threadLocal.get();
        if (messageDigest == null) {
            messageDigest = getMessageDigest();
            threadLocal.set(messageDigest);
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] input = new byte[1024 * 512];
            for (int i = in.read(input); i > 0; i = in.read(input)) {
                messageDigest.update(input, 0, i);
            }
            return messageDigest.digest();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static byte[] getMD5New(InputStream in) throws IOException, NoSuchAlgorithmException {
        MessageDigest messageDigest = threadLocal.get();
        if (messageDigest == null) {
            messageDigest = getMessageDigest();
            threadLocal.set(messageDigest);
        }

        try {

            byte[] input = new byte[1024 * 512];
            for (int i = in.read(input); i > 0; i = in.read(input)) {
                messageDigest.update(input, 0, i);
            }
            return messageDigest.digest();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 查询字符串md5
     *
     * @author     lizhixin
     * @date       2021/8/9 17:41
     */
    public static byte[] getMD5Byte(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = threadLocal.get();
        if (messageDigest == null) {
            messageDigest = getMessageDigest();
            threadLocal.set(messageDigest);
        }
        byte[] input = str.getBytes("UTF-8");
        return messageDigest.digest(input);
    }

    /**
     * 查询数组md5
     *
     * @author     lizhixin
     * @date       2021/8/9 17:41
     */
    public static byte[] getMD5Byte(byte[] bs) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = threadLocal.get();
        if (messageDigest == null) {
            messageDigest = getMessageDigest();
            threadLocal.set(messageDigest);
        }
        return messageDigest.digest(bs);
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
