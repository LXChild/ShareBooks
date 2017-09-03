package me.chong.sharebooksserver.entity;

import lombok.Getter;
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
@Entity
public class Book {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Long classId;

    @Column(nullable = false)
    private String className;

    @Column(unique = true)
    private String no;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();
}
