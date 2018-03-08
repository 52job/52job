package com.job52.dao;

import com.job52.model.Candidate;
import com.job52.model.CandidateKey;

import java.util.List;

public interface CandidateMapper {
    /**
     * 通过主键删除候选人信息
     * @param key
     * @return
     * @throws Exception
     */
     int deleteByPrimaryKey(CandidateKey key) throws Exception;

    /**
     * 插入候选人信息
     * @param record
     * @return
     * @throws Exception
     */
     int insert(Candidate record) throws Exception;

    /**
     * 通过部分属性插入候选人信息
     * @param record
     * @return
     * @throws Exception
     */
     int insertSelective(Candidate record) throws Exception;

    /**
     * 通过主键选择候选人信息
     * @param key
     * @return
     * @throws Exception
     */
     Candidate selectByPrimaryKey(CandidateKey key) throws Exception;

    /**
     * 通过模糊属性候选人描述、是否已读查找候选人信息
     * @param candidate
     * @return
     * @throws Exception
     */
     List<Candidate> selectByContainsInfo(Candidate candidate) throws Exception;

    /**
     * 检索所有候选人信息
     * @return
     * @throws Exception
     */
     List<Candidate> selectAllInfo() throws Exception;

    /**
     * 更新候选人信息部分属性
     * @param record
     * @return
     * @throws Exception
     */
     int updateByPrimaryKeySelective(Candidate record) throws Exception;

    /**
     * 通过主键更新候选人
     * @param record
     * @return
     * @throws Exception
     */
     int updateByPrimaryKey(Candidate record) throws Exception;
}