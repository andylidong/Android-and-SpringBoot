package com.andy.heyi.service.good;

import com.andy.heyi.mapper.good.GoodInfoMapper;
import com.andy.heyi.model.good.GoodInfo;
import com.andy.heyi.model.good.GoodType;
import com.andy.heyi.repository.good.GoodInfoRepository;
import com.andy.heyi.repository.good.GoodTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName GoodSericeImpl
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 1:54 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 1:54 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Service("goodService")
@Slf4j
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodInfoRepository goodInfoRepository;

    @Autowired
    private GoodTypeRepository goodTypeRepository;

    @Autowired
    private GoodInfoMapper goodInfoMapper;

    @Override
    public GoodInfo findGoodDetail(Long id) {
        log.info( "GoodServiceImpl, findGoodDetail id = " + id );
        try {
            // 查询商品基本信息
            Optional<GoodInfo> goodInfo = goodInfoRepository.findById( id );
            // 没有数据则返回null
            if (!goodInfo.isPresent()) return null;
            return goodInfo.get();
        } catch (Exception e) {
            log.error( "GoodServiceImpl, findGoodDetail Exception = " + e.getMessage() );
            return null;
        }
    }
}
