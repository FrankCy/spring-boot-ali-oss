package com.test.ali.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-oss
 * @package: com.test.ali.util
 * @email: cy880708@163.com
 * @date: 2018/10/16 上午9:56
 * @mofified By:
 */
public class ThreeDes {

    /**
     * @param args  作者：avery_leo
     */
    private static final String Algorithm = "DESede"; // 定义 加密算法,可用

    // DES,DESede,Blowfish

//  private static final byte[] keybyte = { 0x11, 0x22, 0x4F, 0x58,
//          (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
//          (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40,
//          0x36, (byte) 0xE2 };


    public ThreeDes() {

        Security.addProvider(new com.sun.crypto.provider.SunJCE());

    }

    // keybyte为加密密钥，长度为24字节
    // src为被加密的数据缓冲区（源）
    public  byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // keybyte为加密密钥，长度为24字节
    // src为加密后的缓冲区
    public  byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // 转换成十六进制字符串
    public  String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {

            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + "";
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 添加新安全算法,如果用JCE就要把它添加进去

        System.out.println("---yu----"+(60&255));
        ThreeDes thrdes = new ThreeDes();
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        // 24字节的密钥
        byte[] keyBytes;
        String key="abcdefghijklmnopqrstuvwx";
        keyBytes=key.getBytes();

        String szSrc = "13900000000";

        System.out.println("加密前的字符串:" + szSrc);

        byte[] encoded =thrdes. encryptMode(keyBytes, szSrc.getBytes());
        System.out.println("加密后的字符串:" + new String(encoded));
        System.out.println("----------"+thrdes.byte2hex(encoded));
        byte[] srcBytes =thrdes. decryptMode(keyBytes, encoded);
        System.out.println("解密后的字符串:" + (new String(srcBytes)));

    }

}
