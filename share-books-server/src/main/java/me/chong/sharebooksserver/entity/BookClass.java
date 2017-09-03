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
public class BookClass {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();
}
