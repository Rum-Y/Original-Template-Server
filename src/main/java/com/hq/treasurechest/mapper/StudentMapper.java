package com.hq.treasurechest.mapper;

import com.hq.treasurechest.vo.AwardeeVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    int updateStudentById(AwardeeVo awardeeVo);
}
