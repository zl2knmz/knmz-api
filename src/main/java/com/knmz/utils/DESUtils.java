package com.knmz.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;

/**
 * DESUtils
 *
 * @Author: chenzeping
 * @Date: 2019/7/25 13:47
 */
public class DESUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(DESUtils.class);

    private static final String KEY = "kT9Cw2Mx";
    private static final String CHARSET = "UTF8";

    public static String encrypt(String plainText) {
        String cipherText = null;
        if (StringUtils.isNotBlank(plainText)) {
            try {
                DESKeySpec dks = new DESKeySpec(KEY.getBytes(CHARSET));
                SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(dks);
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(KEY.getBytes()));
                byte[] enc = cipher.doFinal(plainText.getBytes(CHARSET));
                cipherText = DatatypeConverter.printHexBinary(enc);
            } catch (Exception e) {
                LOGGER.error("encrypt take error, when plainText={}", plainText, e);
            }
        }
        return cipherText;
    }

    public static String decrypt(String cipherText) {
        String plainText = null;
        try {
            byte[] cipherBytes = DatatypeConverter.parseHexBinary(cipherText);
            DESKeySpec dks = new DESKeySpec(KEY.getBytes(CHARSET));
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(KEY.getBytes()));
            byte[] plainBytes = cipher.doFinal(cipherBytes);
            plainText = new String(plainBytes, CHARSET);
        } catch (Exception e) {
            LOGGER.error("decrypt take error, when cipherText={}", cipherText, e);
        }
        return plainText;
    }

    public static String decrypt4dotnet(String cipherText) {
        String ret = null;
        if (StringUtils.isNotBlank(cipherText) && cipherText.length() > 2) {
            String plainText = decrypt(cipherText.substring(2));
            if (StringUtils.isNotBlank(plainText)) {
                ret = plainText;
            }
        }
        return ret;
    }

    public static String encrypt4dotnet(String plainText) {
        String ret = null;
        if (StringUtils.isNotBlank(plainText)) {
            String cipherText = encrypt(plainText);
            if (StringUtils.isNotBlank(plainText)) {
                ret = "11" + cipherText;
            }
        }
        return ret;
    }

}
