package me.chong.sharebookscommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.chong.sharebookscommon.enums.BookBorrowStatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class BookBorrowInfo extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String status;

    public void setStatus(BookBorrowStatusEnum statusEnum) {
        this.status = statusEnum.getTag();
    }
}
