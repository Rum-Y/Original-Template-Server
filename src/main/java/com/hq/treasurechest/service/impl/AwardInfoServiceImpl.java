package com.hq.treasurechest.service.impl;

import com.hq.treasurechest.entity.PersonOfSchool;
import com.hq.treasurechest.mapper.AwardInfoMapper;
import com.hq.treasurechest.service.AwardInfoService;
import com.hq.treasurechest.vo.AwardInfoVo;
import com.hq.treasurechest.vo.AwardeeVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardInfoServiceImpl implements AwardInfoService {
    @Resource
    private AwardInfoMapper awardInfoMapper;
    @Override
    public List<AwardInfoVo> findAllAwardPerSchool(String schoolCode) {
        return awardInfoMapper.findAllAwardInfoBySchoolCode(schoolCode);
    }

    @Override
    public List<AwardeeVo> findAwardee(String schoolCode, String projectCode, String teamIndex) {
        return awardInfoMapper.findAwardeeBySPT(schoolCode, projectCode, teamIndex);
    }
}
