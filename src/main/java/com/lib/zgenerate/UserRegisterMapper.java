package com.lib.zgenerate;

import com.lib.zgenerate.UserRegister;

public interface UserRegisterMapper {
    int deleteByPrimaryKey(Long registerId);

    int insert(UserRegister record);

    int insertSelective(UserRegister record);

    UserRegister selectByPrimaryKey(Long registerId);

    int updateByPrimaryKeySelective(UserRegister record);

    int updateByPrimaryKey(UserRegister record);
}