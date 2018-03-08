package com.job52.service;

import com.job52.model.Resume;

import java.util.List;

public interface ResumeService {

    /**
     * 增加一个简历
     * @param resume
     * @return
     */
    public int addResume(Resume resume);


    /**
     * 删除一个简历
     * @param rid
     * @return
     */
    public int removeResume(String rid);

    /**
     * 更改一个简历
     * @param resume
     * @return
     */
    public int updateResume(Resume resume);

    /**
     * 查询所有简历信息
     * @return
     */
    List<Resume> queryAll();

    List<Resume> queryAll(Resume resume);

    /**
     * 精确查询一个简历信息
     * @param resume
     * @return
     */
//    List<Resume> query(Resume resume);

    /**
     * 按id查询一个简历信息
     * @param rid
     * @return
     */
    public Resume getResume(String rid);

    /**
     * 设置简历状态
     * @param rid
     * @param ispublic
     * @return
     */
    public int setResumeState(String rid, int ispublic);
}