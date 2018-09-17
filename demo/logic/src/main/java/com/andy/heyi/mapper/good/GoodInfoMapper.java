package com.andy.heyi.mapper.good;

import com.andy.heyi.dto.good.GoodInfoDTO;
import com.andy.heyi.model.good.GoodInfo;
import com.andy.heyi.model.good.GoodType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @ClassName GoodInfoMapper
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 1:50 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 1:50 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
// default: 这是默认的情况，mapstruct不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。 GoodInfoMapper MAPPER = Mappers.getMapper( GoodInfoMapper.class );
// cdi: the generated mappers is an application-scoped CDI bean and can be retrieved via @Inject
// spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
// jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取。
@Mapper(componentModel = "spring")
public interface GoodInfoMapper {
    @Mappings(value = {
            @Mapping(source = "good.id", target = "goodId"),
            @Mapping(source = "good.title", target = "goodName"),
            @Mapping(source = "good.price", target = "goodPrice"),
            @Mapping(source = "type.name", target = "typeName")
    })
    GoodInfoDTO from(GoodInfo good, GoodType type);
}