package com.job52.dao;

import com.job52.model.Enterprise;

import java.util.List;

public interface EnterpriseMapper {
    /**
     * 通过主键删除公司信息
     * @param eid
     * @return
     */
    int deleteByPrimaryKey(String eid) throws Exception;

    /**
     * 插入公司信息
     * @param record
     * @return
     */
    int insert(Enterprise record) throws Exception;

    /**
     * 通过部分属性插入公司信息
     * @param record
     * @return
     */
    int insertSelective(Enterprise record) throws Exception;

    /**
     * 通过主键选择公司信息
     * @param eid
     * @return
     */
    Enterprise selectByPrimaryKey(String eid) throws Exception;

    /**
     * 通过模糊属性公司名称、地址、类型、描述查找公司信息
     * @param enterprise
     * @return
     * @throws Exception
     */
    List<Enterprise> selectByContainsInfo(Enterprise enterprise) throws Exception;

    /**
     * 检索所有公司信息
     * @return
     * @throws Exception
     */
    List<Enterprise> selectAllInfo() throws Exception;

    /**
     * 更新公司信息部分属性
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Enterprise record) throws Exception;

    /**
     * 通过主键更新公司
     * @param record
     * @return
     */
    int updateByPrimaryKey(Enterprise record) throws Exception;
}