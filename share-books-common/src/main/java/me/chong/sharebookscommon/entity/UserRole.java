package me.chong.sharebookscommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class UserRole extends BaseEntity {

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
