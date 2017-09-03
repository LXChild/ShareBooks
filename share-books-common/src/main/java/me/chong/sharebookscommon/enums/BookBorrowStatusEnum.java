package me.chong.sharebookscommon.enums;

import lombok.Getter;
import lombok.Setter;

public enum  BookBorrowStatusEnum {
    APPLYING(1, "APPLYING", "申请中"), BORROWING(2, "BORROWING", "租借中"), BACKED(3, "BACKED", "已还书");

    @Getter
    @Setter
    private Integer index;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String comment;

    BookBorrowStatusEnum(Integer index, String tag, String comment) {
        this.index = index;
        this.tag = tag;
        this.comment = comment;
    }
}
