package com.hq.treasurechest.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Random;

public class GenerateUtil {

    /**
     * 生成一个对于JWT HMAC-SHA算法足够安全的密钥
     */
    public static String getSecretKey() {
        return Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    }

    public static String generateCheckCode(int width, int height) {
        // 带有透明背景的bufferedImage对象
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 创建图形上下文
        Graphics2D graphics = bufferedImage.createGraphics();

        int num1 = new Random().nextInt(10);
        int num2 = new Random().nextInt(10);
        char operator = generateOperatorSymbol();

        // 生成验证问题表达式
        String questionExpression
                = operator == '-' ?
                (num1 > num2 ? " " + num1 + " " + operator + " " + num2 + " " + "=" : " " + num2 + " " + operator + " " + num1 + " " + "=")
                : " " + num1 + " " + operator + " " + num2 + " " + "=";


        // 计算正确答案
        int answer = calculateCorrectAnswer(num1, num2, operator);

        //TODO 把答案存储到redis缓存中并设置超时时间
        RedisTemplateUtil.setCheckCodeValue(String.valueOf(answer));

        // 设置背景色（透明背景）
        graphics.setColor(new Color(255,255,255,0));
        graphics.fillRect(0, 0, width, height);

        // 设置抗锯齿以获得更好的图像质量
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 设置字体
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        // 设置文字颜色
        graphics.setColor(new Color(new Random().nextInt(256),new Random().nextInt(256),new Random().nextInt(256)));
        // 将问题绘制到图片上
        graphics.drawString(questionExpression, 20, 30);
        // 释放资源
        graphics.dispose();

        // 将BufferedImage转换为Base64编码字符串
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        }catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        byte[] imageData = byteArrayOutputStream.toByteArray();

        return java.util.Base64.getEncoder().encodeToString(imageData);
    }

    // 生成计算操作符
    public static char generateOperatorSymbol() {
        char[] operators = {'+', '-', '*'};
        return operators[new Random().nextInt(operators.length)];
    }

    // 计算正确结果
    public static int calculateCorrectAnswer(int num1, int num2, char operator) {
        int result = 0;
        switch (operator) {
            case '+' :
                result = num1 + num2;
                break;
            case '-' :
                result = num1 > num2 ? num1 - num2 : num2 - num1;
                break;
            case '*' :
                result = num1 * num2;
                break;
        }
        return result;
    }

    /**
     * 生成系统当前时间
     */
    public static String generateSystemTime() {
        // 获取当前系统时间日期
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // 输出当前系统时间日期
        // 使用 SimpleDateFormat 格式化时间日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }
}
