package com.hq.treasurechest.mapper;

import com.hq.treasurechest.vo.AwardeeVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    int idIsExist(String awardeeId);

    int updateTeacherById(AwardeeVo awardeeVo);
}
