package com.job52.service;

import com.job52.model.*;

import java.util.List;

public interface CollectionService {
    //我的收藏

    /**
     * 获取用户收藏列表
     * @return 收藏列表
     * @throws Exception 异常信息
     */
    public List<Job> findAllCollection(String pid) throws Exception;

    /**
     * 删除收藏列表中的内容
     * @param ids 所有需要删除的收藏的id值
     * @return  操作是否成功
     * @throws Exception
     */
    public long deleteCollection(String ids,String pid) throws Exception;


    public boolean addApplication(List<Application> ids) throws Exception;


    public void addCollection(Collection key) throws Exception;
}
