package com.hq.treasurechest.service;

import com.hq.treasurechest.entity.PersonOfSchool;
import com.hq.treasurechest.vo.AwardInfoVo;
import com.hq.treasurechest.vo.AwardeeVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AwardInfoService {
    List<AwardInfoVo> findAllAwardPerSchool(String schoolCode);

    List<AwardeeVo> findAwardee(String schoolCode, String projectCode, String teamIndex);
}
