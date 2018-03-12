package com.job52.service.impl;

import com.job52.dao.ResumeMapper;
import com.job52.model.Resume;
import com.job52.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("resumeService")
@Transactional
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;


    public int addResume(Resume resume){
        return resumeMapper.insert(resume);
    }

    public int removeResume(String rid) {
        return resumeMapper.deleteByPrimaryKey(rid);
    }

    public int updateResume(Resume resume) {
        return resumeMapper.updateByPrimaryKey(resume);
    }

    public List<Resume> queryAll(Resume resume) {
        return resumeMapper.queryAll(resume);
    }

    public Resume getResume(String rid){
        return resumeMapper.selectByPrimaryKey(rid);
    }

    public int setResumeState(String rid,int ispublic) {
        return resumeMapper.setStateByPrimaryKey(rid,ispublic);
    }
}
