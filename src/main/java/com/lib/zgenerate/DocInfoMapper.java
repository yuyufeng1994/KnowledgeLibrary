package com.lib.zgenerate;

import com.lib.zgenerate.DocInfo;

public interface DocInfoMapper {
    int deleteByPrimaryKey(Long docId);

    int insert(DocInfo record);

    int insertSelective(DocInfo record);

    DocInfo selectByPrimaryKey(Long docId);

    int updateByPrimaryKeySelective(DocInfo record);

    int updateByPrimaryKey(DocInfo record);
}