package com.andy.heyi.model.good;

import javax.persistence.*;

/**
 * @ClassName GoodInfo
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 1:44 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 1:44 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Entity
@Table(name = "good_infos")
public class GoodInfo {

    @Id
    @Column(name = "tg_id")
    private Long id;

    @Column(name = "tg_title")
    private String title;

    @Column(name = "tg_price")
    private double price;

    @Column(name = "tg_order")
    private int order;

    @JoinColumn(name = "tg_type_id", referencedColumnName = "tgt_id")
    @ManyToOne
    private GoodType goodType;

    private String typeName;

    public GoodInfo() {
    }

    public GoodInfo(Long id, String title, double price, int order, String typeName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.order = order;
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public void setGoodType(GoodType goodType) {
        this.goodType = goodType;
    }

    public String getTypeName() {
        return typeName;/**/
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
