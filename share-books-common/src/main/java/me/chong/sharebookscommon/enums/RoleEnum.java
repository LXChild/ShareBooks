package me.chong.sharebookscommon.enums;

import lombok.Getter;
import lombok.Setter;

public enum RoleEnum {
    SYS_ADMIN(1, "ROLE_SYS_ADMIN", "系统管理员"), ADMIN(2, "ROLE_ADMIN", "管理员"),
    USER(3, "ROLE_USER", "用户");

    @Getter
    @Setter
    private Integer index;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String comment;

    RoleEnum(Integer index, String tag, String comment) {
        this.index = index;
        this.tag = tag;
        this.comment = comment;
    }
}
