package com.lib.zgenerate;

import com.lib.zgenerate.FileInfo;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Long fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKeyWithBLOBs(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
}