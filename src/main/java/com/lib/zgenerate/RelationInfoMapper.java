package com.lib.zgenerate;

import com.lib.zgenerate.RelationInfo;
import com.lib.zgenerate.RelationInfoKey;

public interface RelationInfoMapper {
    int deleteByPrimaryKey(RelationInfoKey key);

    int insert(RelationInfo record);

    int insertSelective(RelationInfo record);

    RelationInfo selectByPrimaryKey(RelationInfoKey key);

    int updateByPrimaryKeySelective(RelationInfo record);

    int updateByPrimaryKey(RelationInfo record);
}