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
public class BookClass extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
