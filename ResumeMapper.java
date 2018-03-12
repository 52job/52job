package com.job52.dao;

import com.job52.model.Resume;

import java.util.List;

public interface ResumeMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Resume resume);

    int insertSelective(Resume record);

    Resume selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKey(Resume record);

    int setStateByPrimaryKey(String rid, int ispublic);

    List<Resume> queryAll(Resume resume);
}