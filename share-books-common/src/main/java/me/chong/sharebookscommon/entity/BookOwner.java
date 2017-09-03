package me.chong.sharebookscommon.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class BookOwner extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookId;

    public BookOwner(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
