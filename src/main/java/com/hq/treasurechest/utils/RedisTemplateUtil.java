package com.hq.treasurechest.utils;

import com.hq.treasurechest.common.CustomConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisTemplateUtil {

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisTemplateUtil.redisTemplate = redisTemplate;
    }

    /**
     * 存储CheckCode答案
     */
    public static void setCheckCodeValue(String value) {
        redisTemplate.opsForValue().set(CustomConstant.CHECK_CODE_KEY, value, 1, TimeUnit.MINUTES);
    }

    /**
     * 获取CheckCode答案
     */
    public static String getCheckCodeAnswer() {
        return redisTemplate.opsForValue().get(CustomConstant.CHECK_CODE_KEY);
    }

    /**
     * 移除缓存中的CheckCodeAnswer
     */
    public static void removeCheckCodeAnswer() {
        redisTemplate.delete(CustomConstant.CHECK_CODE_KEY);
    }

    /**
     * 检验checkCode答案是否过期
     */
    public static boolean isExpired() {
        Long expire = redisTemplate.getExpire(CustomConstant.CHECK_CODE_KEY);
        return expire != null && expire < 0; // 如若存在该键，再判断是否过期（expire小于0表示过期）
    }

}
