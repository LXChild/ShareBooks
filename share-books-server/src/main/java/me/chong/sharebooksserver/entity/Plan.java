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
public class Plan {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String goal;

    @Column(nullable = false)
    private String dailyGoal;

    @Column(nullable = false)
    private String awards;

    @Column(nullable = false)
    private String finalAwards;

    @Column(nullable = false)
    private Integer readCount = 0;

    @Column(nullable = false)
    private Boolean complete = false;

    @Column(nullable = false)
    protected Date createTime;

    @Column(nullable = false)
    protected Date updateTime = new Date();
}
