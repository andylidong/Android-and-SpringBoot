package com.andy.heyi.common.exception;


import com.andy.heyi.util.result.ResultCode;
import com.andy.heyi.util.result.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ExceptionAdvice
 * @Description: 所有请求的异常处理
 * @Author: lidong
 * @CreateDate: 2018/8/10$ 5:00 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/10$ 5:00 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class ExceptionAdvice {

    /**
     * 默认统一异常处理方法
     *
     * @param e 默认Exception异常对象
     * @return
     */
    @ExceptionHandler
    @ResponseStatus
    public String runtimeExceptionHandler(Exception e) {
        return ResultUtil.error( ResultCode.FAILURE, e.getMessage() );
    }
}
