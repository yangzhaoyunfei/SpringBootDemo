package com.dudu.service;

import com.dudu.domain.LearnResource;
import com.dudu.model.LeanQueryLeanListReq;
import com.dudu.util.Page;

import java.util.List;

/**
 * Created by tengj on 2017/4/7.
 */

public interface LearnService extends IService<LearnResource> {

    /**
     * 列表查询
     *
     * @param page
     * @return
     */
    public List<LearnResource> queryLearnResouceList(Page<LeanQueryLeanListReq> page);

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteBatch(Long[] ids);

    /**
     * 批量添加
     */
    public void addBatch();
}
