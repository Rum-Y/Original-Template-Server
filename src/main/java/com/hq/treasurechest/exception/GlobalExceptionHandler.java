package com.hq.treasurechest.exception;

import net.sf.jsqlparser.JSQLParserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 输入的用户名或者密码错误时触发
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e) {
        // 返回 HTTP 状态码 401 Unauthorized 和错误信息给前端
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误，请重试。");
    }

    /*@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleUserAccessDeniedException(AccessDeniedException e) {
        // 返回 HTTP 状态码 401 Unauthorized 和错误信息给前端
        System.out.println("捕获权限不足异常");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("没有权限访问或权限不足");
    }*/

    /**
     * 发送sql语句解析异常时触发
     */
    @ExceptionHandler(JSQLParserException.class)
    public ResponseEntity<?> handleJSQLParserException(JSQLParserException e) {
        System.out.println("JSQLParserException全局异常捕获到错误："+e.getMessage());
        // 返回 HTTP 状态码 401 Unauthorized 和错误信息给前端
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        System.out.println("Exception全局异常捕获到错误："+e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }*/

}
