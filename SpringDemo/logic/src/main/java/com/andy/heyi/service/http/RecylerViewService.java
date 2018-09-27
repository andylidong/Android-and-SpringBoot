package com.andy.heyi.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @InterfaceName RecylerViewService
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/9/26$ 3:55 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/9/26$ 3:55 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@FeignClient(name = "recylerViewService", url = "${api.recylerview}", fallback = Exception.class)
public interface RecylerViewService {

    @RequestMapping(value = "api/data/福利/10/1")
    Object data();
}
