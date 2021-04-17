package com.portfolio.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_no")
    private Long id;
    @Column(name = "member_id")
    private String email;
    @Column(name = "member_pw")
    private String password;
    @Column(name = "member_name")
    private String name;
    @Column(name = "member_mail_certified")
    @ColumnDefault("0")
    private boolean mailCertified;
    @Column(name = "member_auth")
    @ColumnDefault("0")
    private boolean auth;

}
