package com.portfolio.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_no")
    private Long id;

    @Column(name = "member_id",unique = true)
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Column(name = "member_pw")
    private String password;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Column(name = "member_name", unique = true)
    private String name;

    @Column(name = "member_mail_certified")
    @ColumnDefault("0")
    private boolean mailCertified;

    @Column(name = "member_auth")
    @ColumnDefault("0")
    private boolean auth;

}
