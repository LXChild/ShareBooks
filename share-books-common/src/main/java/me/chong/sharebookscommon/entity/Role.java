package me.chong.sharebookscommon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.chong.sharebookscommon.enums.RoleEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Role extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String tag;

    @Column(nullable = false, unique = true)
    private String comment;

    public Role(String tag, String comment) {
        this.tag = tag;
        this.comment = comment;
    }

    public Role(String tag, String comment, Date createTime) {
        this.tag = tag;
        this.comment = comment;
        this.createTime = createTime;
    }

    public Role(RoleEnum roleEnum) {
        this.tag = roleEnum.getTag();
        this.comment = roleEnum.getComment();
    }
}
