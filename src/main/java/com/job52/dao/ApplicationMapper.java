package com.job52.dao;

import com.job52.model.Application;
import com.job52.model.ApplicationKey;

import java.util.List;

public interface ApplicationMapper {
    int deleteByPrimaryKey(ApplicationKey key);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(ApplicationKey key);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    /**
     * 按照对应的model不为空的属性进行匹配查询
     * @return
     */
    List<Application> query(Application application);

    /**
     * 按照对应的model不为空的属性进行模糊查询
     * @return
     */
    List<Application> queryContains(Application application);

}