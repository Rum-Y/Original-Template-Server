package com.hq.treasurechest.service.impl;

import com.hq.treasurechest.mapper.StudentMapper;
import com.hq.treasurechest.service.StudentService;
import com.hq.treasurechest.vo.AwardeeVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Override
    public int updateStudentById(AwardeeVo awardeeVo) {
        return studentMapper.updateStudentById(awardeeVo);
    }
}
