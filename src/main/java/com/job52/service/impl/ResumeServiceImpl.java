package com.job52.service.impl;

import com.job52.model.Resume;
import com.job52.service.ResumeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    public int addResume(Resume resume) {
        return 0;
    }

    public int removeResume(String rid) {
        return 0;
    }

    public int updateResume(Resume resume) {
        return 0;
    }

    public List<Resume> queryAll() {
        return null;
    }

    public List<Resume> queryAll(Resume resume) {
        return null;
    }

    public Resume getResume(String rid) {
        return null;
    }

    public int setResumeState(String rid, int ispublic) {
        return 0;
    }

    public int setResumeState(Resume resume) {
        return 0;
    }
}
