package com.job52.service;

import com.job52.model.Application;
import com.job52.model.ApplicationKey;

import java.util.List;

public interface ApplicationService {

    /**
     * 添加一条申请信息
     * @param application
     * @return
     */
    boolean addApplication(Application application);

    /**
     * 删除一条申请信息
     * @param applicationKey
     * @return
     */
    boolean removeApplication(ApplicationKey applicationKey);

    /**
     * 更新申请信息
     * @param application
     * @return
     */
    boolean updateApplication(Application application);

    /**
     * 查询所有申请信息
     * @return
     */
    List<Application> queryAll();

    /**
     * 根据application有的字段的值匹配查询
     * @param application
     * @return
     */
    List<Application> query(Application application);

    /**
     * 根据application有的字段的值模糊查询
     * @param application
     * @return
     */
    List<Application> queryContains(Application application);

}
