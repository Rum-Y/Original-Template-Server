package com.hq.treasurechest.service.impl;

import com.hq.treasurechest.mapper.TeacherMapper;
import com.hq.treasurechest.service.TeacherService;
import com.hq.treasurechest.vo.AwardeeVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public int idIsExist(String awardeeId) {
        return teacherMapper.idIsExist(awardeeId);
    }

    @Override
    public int updateTeacherById(AwardeeVo awardeeVo) {
        return teacherMapper.updateTeacherById(awardeeVo);
    }
}
