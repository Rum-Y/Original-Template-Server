package com.hq.treasurechest.service;

import com.hq.treasurechest.vo.AwardeeVo;

public interface TeacherService {
    // 判断id是否存在于表中
    int idIsExist(String awardeeId);

    int updateTeacherById(AwardeeVo awardeeVo);
}
