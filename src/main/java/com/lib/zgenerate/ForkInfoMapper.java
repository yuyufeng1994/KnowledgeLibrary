package com.lib.zgenerate;

import com.lib.zgenerate.ForkInfo;

public interface ForkInfoMapper {
    int deleteByPrimaryKey(Long forkId);

    int insert(ForkInfo record);

    int insertSelective(ForkInfo record);

    ForkInfo selectByPrimaryKey(Long forkId);

    int updateByPrimaryKeySelective(ForkInfo record);

    int updateByPrimaryKey(ForkInfo record);
}