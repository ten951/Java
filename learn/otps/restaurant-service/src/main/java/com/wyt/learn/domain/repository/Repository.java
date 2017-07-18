package com.wyt.learn.domain.repository;

/**
 * 全的存储库
 *
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public interface Repository<TE, T> extends ReadOnlyRepository<TE, T> {
    /**
     * 序列化实体
     *
     * @param entity 实体
     */
    void add(TE entity);

    /**
     * 删除实体
     *
     * @param id 唯一标识符
     */
    void remove(T id);

    /**
     * 更新实体
     *
     * @param entity 实体
     */
    void update(TE entity);
}
