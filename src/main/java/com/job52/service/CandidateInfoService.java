package com.job52.service;

import com.job52.model.Candidate;

import java.util.List;

public interface CandidateInfoService {
    /**
     * 新增候选人信息
     * @param candidate
     * @return
     */
    boolean addCandidate(Candidate candidate);

    /**
     * 删除候选人信息
     * @param candidate
     * @return
     */
    boolean removeCandidate(Candidate candidate) throws Exception;

    /**
     * 批量删除候选人信息
     * @param jids
     * @param pids
     * @return
     * @throws Exception
     */
    public boolean removeCandidates(List<String> jids, List<String> pids) throws Exception;

    /**
     * 更新候选人信息
     * @param candidate
     * @return
     */
    boolean updateCandidate(Candidate candidate) throws Exception;

    /**
     * 获取具体候选人信息
     * @param candidate
     * @return
     */
    Candidate getCandidate(Candidate candidate);

    /**
     * 模糊查询候选人信息
     * @param candidate
     * @return
     */
    List<Candidate> queryContainsCandidate(Candidate candidate);

    /**
     * 查询所有候选人信息
     * @return
     */
    List<Candidate> queryAllCandidate();
}
