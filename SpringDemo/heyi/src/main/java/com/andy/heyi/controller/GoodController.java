package com.andy.heyi.controller;

import com.andy.heyi.common.version.ApiVersion;
import com.andy.heyi.service.good.GoodService;
import com.andy.heyi.service.http.UserHttpService;
import com.andy.heyi.util.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName GoodController
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 2:05 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 2:05 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@RestController
@ApiOperation(value = "Good", notes = "商品信息")
@ApiVersion(1)
public class GoodController {

    @Resource
    private GoodService goodService;

    @Autowired
    private UserHttpService userHttpService;

    @GetMapping(value = "detail")
    public String demo(@RequestParam(value = "id", defaultValue = "1") Long id) {
        System.out.println( " detail = " + userHttpService.demo( "I am d demo " ) );
        return ResultUtil.success( goodService.findGoodDetail( id ) );
    }
}
