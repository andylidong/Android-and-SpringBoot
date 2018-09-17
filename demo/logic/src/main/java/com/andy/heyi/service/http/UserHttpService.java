package com.andy.heyi.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @InterfaceName UserHttpService
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/9/5$ 2:27 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/9/5$ 2:27 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@FeignClient(name = "userHttpService", url = "${api.address}", fallback = Exception.class)
public interface UserHttpService {

    @GetMapping(value = "api/v1/demo")
    @ResponseBody
    String demo(@RequestParam(value = "name") String name);
}
