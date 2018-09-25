package com.andy.heyi.util.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Result
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/7/19$ 11:46 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/7/19$ 11:46 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Data
@ToString
@AllArgsConstructor
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(int i, String s) {
    }


}
