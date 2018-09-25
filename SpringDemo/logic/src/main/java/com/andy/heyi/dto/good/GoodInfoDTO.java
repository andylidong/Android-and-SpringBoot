package com.andy.heyi.dto.good;

/**
 * @ClassName GoodInfoDTO
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 1:47 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 1:47 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class GoodInfoDTO {
    // 商品编号
    private String goodId;

    // 商品名称
    private String goodName;

    // 商品价格
    private double goodPrice;

    // 类型名称
    private String typeName;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
