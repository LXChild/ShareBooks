package me.chong.sharebooksserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean enable = true;

    @Column(nullable = false)
    private String realName;

    @Column
    private String remark;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.enable = user.getEnable();
        this.realName = user.getRealName();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
    }
}
