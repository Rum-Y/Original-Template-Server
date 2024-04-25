package com.hq.treasurechest.service;

import com.hq.treasurechest.dto.LoginDTO;
import com.hq.treasurechest.vo.UserInfoVo;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    HashMap<String, String> login(LoginDTO loginDTO);

    void enterLoginTime(String schoolCode);

    List<UserInfoVo> getAllUserinfo();

    void lockUserBySchoolCode(String schoolCode);

    void unlockUserBySchoolCode(String schoolCode);
}
