package com.portfolio.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Content {

    @Id @GeneratedValue
    @Column(name = "content_id")
    private Long id;

    @Column(name = "content_subject", nullable = false)
    private String subject;

    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Lob
    @Column(name = "content_text")
    private String text;

    @Column(name = "content_deleted")
    private boolean deleted;

    @Column(name = "content_hit",columnDefinition = "integer default 0")
    private int hitCount;

    @OneToMany(mappedBy = "content")
    private List<ContentStatus> contentStatusList = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "content_datetime")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "content_modify_datetime")
    private LocalDateTime modifyTime;
}
