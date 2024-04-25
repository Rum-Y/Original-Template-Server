package com.hq.treasurechest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.treasurechest.entity.UserInfo;
import com.hq.treasurechest.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
    UserInfo findUserAndRolesByUsername(String username);

    String IsFirstLogin(String schoolCode);

    void setFirstAndLastLoginTime(String schoolCode, String systemNowTime);

    void setLastLoginTime(String schoolCode, String systemNowTime);

    List<UserInfoVo> findAllUserinfo();

    void updateLockStateToLock(String schoolCode);

    void updateLockStateToUnLock(String schoolCode);
}
