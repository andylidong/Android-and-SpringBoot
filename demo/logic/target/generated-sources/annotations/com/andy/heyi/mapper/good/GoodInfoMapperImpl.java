package com.andy.heyi.mapper.good;

import com.andy.heyi.dto.good.GoodInfoDTO;

import com.andy.heyi.model.good.GoodInfo;

import com.andy.heyi.model.good.GoodType;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2018-09-05T14:50:55+0800",

    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"

)

@Component

public class GoodInfoMapperImpl implements GoodInfoMapper {

    @Override

    public GoodInfoDTO from(GoodInfo good, GoodType type) {

        if ( good == null && type == null ) {

            return null;
        }

        GoodInfoDTO goodInfoDTO = new GoodInfoDTO();

        if ( good != null ) {

            if ( good.getId() != null ) {

                goodInfoDTO.setGoodId( String.valueOf( good.getId() ) );
            }

            goodInfoDTO.setGoodName( good.getTitle() );

            goodInfoDTO.setGoodPrice( good.getPrice() );
        }

        if ( type != null ) {

            goodInfoDTO.setTypeName( type.getName() );
        }

        return goodInfoDTO;
    }
}

