package com.hq.treasurechest.service.impl;

import com.hq.treasurechest.entity.UserInfo;
import com.hq.treasurechest.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    // 通常情况下，这个方法的主要作用是根据用户名获取用户的基本信息，如用户名、密码、角色等，并返回一个 UserDetails 对象，用于后续的认证和授权。
    // 一般不需要在此处再进行密码校验，security自动执行校验
    @Override
    public UserDetails loadUserByUsername(String schoolCode) throws UsernameNotFoundException { // 向上抛异常

        // 根据传递过来的用户名查询用户信息
        UserInfo user = userMapper.findUserAndRolesByUsername(schoolCode);

        if (user == null)
        {
            throw new UsernameNotFoundException("User not found with schoolCode: " + schoolCode);
        }

        // 封装UserDetails并返回
        return user;
    }
}
