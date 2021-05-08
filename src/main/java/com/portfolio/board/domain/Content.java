package com.portfolio.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Content {

    @Id @GeneratedValue
    @Column(name = "content_id")
    private Long id;

    @Column(name = "content_subject")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @Lob
    @Column(name = "content_text")
    private String text;

    @Column(name = "content_deleted")
    private boolean deleted;

    @CreationTimestamp
    @Column(name = "content_datetime")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "content_modify_datetime")
    private LocalDateTime modifyTime;
}
