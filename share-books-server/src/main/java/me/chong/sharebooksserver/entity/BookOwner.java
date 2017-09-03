package me.chong.sharebooksserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class BookOwner {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();
}
