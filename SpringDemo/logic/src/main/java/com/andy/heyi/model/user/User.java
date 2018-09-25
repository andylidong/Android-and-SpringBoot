package com.andy.heyi.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


/**
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
// @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})  注解把不需要序列化的属性屏蔽掉
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Column(name = "password")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String password;

    /**
     * 时间数据库自动生成
     */
    @Column(name = "lastLoginTime", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastLoginTime;
}
