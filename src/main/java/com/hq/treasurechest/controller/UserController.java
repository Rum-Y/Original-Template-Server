package com.hq.treasurechest.controller;

import com.hq.treasurechest.common.CustomConstant;
import com.hq.treasurechest.common.ResultEntity;
import com.hq.treasurechest.dto.LoginDTO;
import com.hq.treasurechest.service.UserService;
import com.hq.treasurechest.utils.GenerateUtil;
import com.hq.treasurechest.utils.RedisTemplateUtil;
import com.hq.treasurechest.vo.UserInfoVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public ResultEntity<Map<String,String>> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

        // 检验认证码是否过期
        if (RedisTemplateUtil.isExpired()) { // 过期
            RedisTemplateUtil.removeCheckCodeAnswer(); // 移除
            return ResultEntity.failed(CustomConstant.FAILED_CODE, "验证码已过期，请点击验证码刷新");
        }
        // 没过期，验证用户输入的答案是否正确
        if (! RedisTemplateUtil.getCheckCodeAnswer().equals(loginDTO.getCheckCode())) // 不一致
            return ResultEntity.failed(CustomConstant.FAILED_CODE, "验证码输入错误，请重新输入");

        Map<String, String> result = userService.login(loginDTO);

        if (result == null) {
            return ResultEntity.failed(CustomConstant.FAILED_CODE, "用户已被锁定, 请联系系统管理员解锁");
        } else {
            // 登录成功录入登录时间
            userService.enterLoginTime(result.get("userSchoolCode"));
            return ResultEntity.success(CustomConstant.SUCCESS_CODE, "登录成功", result);
        }
    }

    @GetMapping("/checkCode")
    public ResultEntity<String> getCheckCode() {
        String checkCode = GenerateUtil.generateCheckCode(100, 40);
        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "获取验证码成功", checkCode);
    }

    @GetMapping("/users")
    public ResultEntity<List<UserInfoVo>> getAllUserinfo() {
        List<UserInfoVo> allUserinfo = userService.getAllUserinfo();

        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "获取成功", allUserinfo);
    }

    @GetMapping("/lock")
    public ResultEntity<String> lockUser(String schoolCode) {
        userService.lockUserBySchoolCode(schoolCode);
        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "锁定用户成功");
    }

    @GetMapping("/unlock")
    public ResultEntity<String> unlockUser(String schoolCode) {
        userService.unlockUserBySchoolCode(schoolCode);
        return ResultEntity.success(CustomConstant.SUCCESS_CODE, "解锁用户成功");
    }

}
