package com.ssm.rest.demo.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodecUtil {
	private static Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将 URL 编码
     */
    public static String encodeURL(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     */
    public static String decodeURL(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 编码
     */
    public static String encodeBASE64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 Base64 解码
     */
    public static String decodeBASE64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将字符串 MD5 加密
     */
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 将字符串 SHA 加密
     */
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 创建随机数
     */
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * 获取 UUID（32位）
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
    
    public static String encodeSHA256(String key, String content) {
    	try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes("utf-8");
            byte[] dataBytes = content.getBytes("utf-8");

            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
            mac.init(secret);

            byte[] doFinal = mac.doFinal(dataBytes);
            byte[] hexB = new Hex().encode(doFinal);
            return new String(hexB, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public static String encodeSHA256(String key, Map<String, ?> map) {
        StringBuilder s = new StringBuilder();
        for(Object values : map.values()) {
            if(values instanceof String[]) {
                for(String value : (String[])values) {
                    s.append(value);
                }
            } else if(values instanceof List) {
                for(String value : (List<String>)values) {
                    s.append(value);
                }
            } else {
                s.append(values);
            }
        }
        return encodeSHA256(key, s.toString());
    }
}
