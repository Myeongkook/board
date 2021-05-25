package com.portfolio.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ContentStatus {

    @Id @GeneratedValue
    @Column(name = "content_status_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "content_created_time")
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content_status_deleted")
    private boolean deleted;
}
