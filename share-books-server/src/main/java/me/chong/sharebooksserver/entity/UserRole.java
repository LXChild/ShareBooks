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
public class UserRole {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private Integer roleId;

    public UserRole(Long userId, Integer roleId, Date createTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.createTime = createTime;
    }
}
