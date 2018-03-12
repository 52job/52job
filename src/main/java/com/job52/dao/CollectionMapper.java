package com.job52.dao;

import com.job52.model.Collection;
import com.job52.model.CollectionKey;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(CollectionKey key);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(CollectionKey key);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    /**
     * 查找所有的收藏信息
     * @return 收藏信息
     * @throws Exception
     */
    List<String> findAllCollection(String pid) throws  Exception;
}