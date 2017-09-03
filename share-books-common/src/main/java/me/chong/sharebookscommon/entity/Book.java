package me.chong.sharebookscommon.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Book extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long classId;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private Integer quantity;
}
