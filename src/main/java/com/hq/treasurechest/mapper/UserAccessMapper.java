package com.hq.treasurechest.mapper;

import com.hq.treasurechest.entity.LoginDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccessMapper {
    int insert(LoginDetail loginDetail);
}
