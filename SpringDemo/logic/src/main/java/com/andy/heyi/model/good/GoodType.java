package com.andy.heyi.model.good;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName GoodType
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/8/15$ 1:46 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/15$ 1:46 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
@Entity
@Table(name = "good_types")
public class GoodType {

    @Id
    @Column(name = "tgt_id")
    private Long id;

    @Column(name = "tgt_name")
    private String name;

    @Column(name = "tgt_is_show")
    private int show;

    @Column(name = "tgt_order")
    private int order;

    public GoodType() {
    }

    public GoodType(Long id, String name, int show, int order) {
        this.id = id;
        this.name = name;
        this.show = show;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
