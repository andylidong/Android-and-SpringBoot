package com.andy.heyi.service;

import java.util.List;

/**
 * @InterfaceName IService
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/8$ 3:06 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/8$ 3:06 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface IService<T> {
    /**
     * 数据持久化
     *
     * @param model
     */
    T save(T model);

    /**
     * 批量持久化
     *
     * @param models
     */
    void save(List<T> models);

    /**
     * 更新
     *
     * @param model
     */
    T update(T model);

    /**
     * 更新
     *
     * @param model
     */
    void update(List<T> model);

    /**
     * 通过主鍵刪除
     *
     * @param id
     */
    T deleteById(Integer id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     *
     * @param ids
     * @return
     */
    List<T> findByIds(List<Integer> ids);

    /**
     * 获取所有
     *
     * @return
     */
    List<T> findAll();

}
