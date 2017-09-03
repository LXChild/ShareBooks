package me.chong.sharebooksserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();
}
