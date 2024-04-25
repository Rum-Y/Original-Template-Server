package com.hq.treasurechest.mapper;

import com.hq.treasurechest.entity.PersonOfSchool;
import com.hq.treasurechest.vo.AwardInfoVo;
import com.hq.treasurechest.vo.AwardeeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AwardInfoMapper {
    List<AwardInfoVo> findAllAwardInfoBySchoolCode(String schoolCode);

    List<AwardeeVo> findAwardeeBySPT(String schoolCode, String projectCode, String teamIndex);
}
