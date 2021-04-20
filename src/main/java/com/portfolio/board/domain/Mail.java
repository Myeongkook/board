package com.portfolio.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Mail {

    @Id @GeneratedValue
    @Column(name = "mail_no")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "mail_cert_no")
    private Integer number;

    @CreationTimestamp
    @Column(name = "mail_send_datetime")
    private LocalDateTime time;
}
