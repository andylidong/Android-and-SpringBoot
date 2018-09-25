package com.andy.heyi.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ArrayUtil
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/10$ 2:43 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/10$ 2:43 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class ArrayUtil {

    /**
     * 将数组转成list，将添加限制，最后返回数据
     *
     * @param data
     * @param pattern
     * @return
     */
    public static List<Object> arratToListWithPattern(Object[] data, String pattern) {
        return Arrays.stream( data ).filter( (item) -> item.equals( pattern ) ).collect( Collectors.toList() );
    }

    /**
     * 将数组转成list，将添加限制，最后返回数据的大小
     *
     * @param data
     * @param pattern
     * @return
     */
    public static int arratToListSizeWithPattern(Object[] data, String pattern) {
        List result = Arrays.stream( data ).filter( (item) -> item.equals( pattern ) ).collect( Collectors.toList() );
        if (EmptyUtil.isNotEmpty( result )) {
            return result.size();
        }
        return 0;
    }
}
