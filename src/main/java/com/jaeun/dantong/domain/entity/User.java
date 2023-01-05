package com.jaeun.dantong.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String major;

    @Column(nullable = false)
    private Integer studentId;

    @Column(length = 10)
    private String status;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards;

    @Builder
    public User(String email,String name, String password, String major, int studentId ) {
        this.status = "INACTIVE";
        this.email = email;
        this.name = name;
        this.password = password;
        this.userRole = UserRole.USER;
        this.major = major;
        this.studentId = studentId;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
