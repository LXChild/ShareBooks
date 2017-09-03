package me.chong.sharebooksserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.chong.sharebooksserver.enums.BookBorrowStatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class BookBorrowInfo {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private String bookName;

    private String bookNo;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();

    public void setStatus(BookBorrowStatusEnum statusEnum) {
        this.status = statusEnum.getTag();
    }
}
