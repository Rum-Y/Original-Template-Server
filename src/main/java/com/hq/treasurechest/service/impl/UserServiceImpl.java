package com.hq.treasurechest.service.impl;

import com.hq.treasurechest.dto.LoginDTO;
import com.hq.treasurechest.entity.UserInfo;
import com.hq.treasurechest.mapper.UserMapper;
import com.hq.treasurechest.service.UserService;
import com.hq.treasurechest.utils.GenerateUtil;
import com.hq.treasurechest.utils.JWTUtil;
import com.hq.treasurechest.vo.UserInfoVo;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserMapper userMapper;

    @Override
    public HashMap<String, String> login(LoginDTO loginDTO) {
        Authentication authentication = null;

        HashMap<String, String> map = null;

        String token = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getSchoolCode(), loginDTO.getPassword());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationToken);
            // 存储经过封装的用户信息
            SecurityContextHolder.setContext(context);
            // 执行校验
            authentication = authenticationManager.authenticate(authenticationToken);

            //TODO 打印SecurityContext上下文中的用户信息

        } finally { // 清空SecurityContext存储的信息
            SecurityContextHolder.clearContext();
        }

        // 登录成功则生成token返回
        if (authentication != null && authentication.isAuthenticated()) // not null and isAuthenticated
        {
            UserInfo user = (UserInfo) authentication.getPrincipal();

            // 判定用户是否被锁定
            if (user.getIsLocked() == '1')
                return null;

            token = JWTUtil.generateToken(user.getSchoolCode());
            map = new HashMap<>();
            map.put("userSchoolCode", user.getSchoolCode());
            map.put("token", token);
        }

        return map;
    }

    @Override
    public void enterLoginTime(String schoolCode) {
        // 是否是第一次登录
        String firstLoginTime = userMapper.IsFirstLogin(schoolCode);

        if (firstLoginTime == null) { // 如果是第一次登录录入两个时间
            userMapper.setFirstAndLastLoginTime(schoolCode, GenerateUtil.generateSystemTime());
        } else { // 否则只录入一个时间
            userMapper.setLastLoginTime(schoolCode, GenerateUtil.generateSystemTime());
        }
    }

    @Override
    public List<UserInfoVo> getAllUserinfo() {
        return userMapper.findAllUserinfo();
    }

    @Override
    public void lockUserBySchoolCode(String schoolCode) {
        userMapper.updateLockStateToLock(schoolCode);
    }

    @Override
    public void unlockUserBySchoolCode(String schoolCode) {
        userMapper.updateLockStateToUnLock(schoolCode);
    }

}
