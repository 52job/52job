package com.job52.service.impl;

import com.job52.dao.CandidateMapper;
import com.job52.model.Candidate;
import com.job52.service.CandidateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidateInfoServiceImpl implements CandidateInfoService {
    /**
     *
     */
    @Autowired
    private CandidateMapper candidateMapper ;

    public boolean addCandidate(Candidate candidate) {
        try {
            candidateMapper.insert(candidate);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeCandidate(Candidate candidate)  {
        try {
            candidateMapper.deleteByPrimaryKey(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Transactional
    public boolean removeCandidates(List<String> jids,List<String> pids)  {
        int len = jids.size();
        for(int i=0; i<len; i++) {
            try {
                candidateMapper.deleteByPrimaryKey(new Candidate(jids.get(i),pids.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean updateCandidate(Candidate candidate) throws Exception {
            candidateMapper.updateByPrimaryKeySelective(candidate);
        return true;
    }

    public Candidate getCandidate(Candidate candidate) {
        Candidate c = null;
        try {
            c = candidateMapper.selectByPrimaryKey(candidate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Candidate> queryContainsCandidate(Candidate candidate) {
        try {
            return candidateMapper.selectByContainsInfo(candidate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Candidate> queryAllCandidate() {
        try {
            return candidateMapper.selectAllInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
