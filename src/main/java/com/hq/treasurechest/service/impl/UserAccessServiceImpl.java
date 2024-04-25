package com.hq.treasurechest.service.impl;

import com.hq.treasurechest.entity.LoginDetail;
import com.hq.treasurechest.mapper.UserAccessMapper;
import com.hq.treasurechest.service.UserAccessService;
import com.hq.treasurechest.utils.IpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class UserAccessServiceImpl implements UserAccessService {

    @Resource
    UserAccessMapper userAccessMapper;

    @Override
    public void enterLoginMsg(HttpServletRequest request, String userId) {
        // 获取登录者的ip，位置信息，设备信息
        LoginDetail loginDetail = new LoginDetail();
        loginDetail.setUserId(userId);
        String ip = IpUtil.getIp(request);
        loginDetail.setIpAddress(ip);
        loginDetail.setLoginLocation(IpUtil.getCityInfo(ip));
        UserAgent userAgent = IpUtil.getUserAgent(request);
        loginDetail.setBrowser(String.valueOf(userAgent.getBrowser()));
        loginDetail.setOs(String.valueOf(userAgent.getOperatingSystem()));
        // 获取当前系统时间日期
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // 使用 SimpleDateFormat 格式化时间日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        loginDetail.setLoginTime(Timestamp.valueOf(sdf.format(timestamp)));

        userAccessMapper.insert(loginDetail); // 插入登录者信息

    }
}
