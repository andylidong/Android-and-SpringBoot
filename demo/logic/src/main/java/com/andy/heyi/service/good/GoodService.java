package com.andy.heyi.service.good;

import com.andy.heyi.model.good.GoodInfo;

/**
 * @InterfaceName GoodService
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 1:54 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 1:54 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public interface GoodService {
    /**
     * 查找商品信息
     *
     * @param id
     * @return
     */
    GoodInfo findGoodDetail(Long id);
}
