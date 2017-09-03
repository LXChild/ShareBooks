package me.chong.sharebooksserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 帖子实体类
 * Created by LXChild on 07/04/2017.
 */
@Getter
@Setter
@ToString
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long creatorId;

    @Column(nullable = false)
    private String creatorName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date createTime;
}
