package com.jaeun.dantong.token;


import com.jaeun.dantong.domain.entity.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "app_user_id")
    private User user;

    protected ConfirmationToken() {}

    private ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public static ConfirmationToken of(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        return new ConfirmationToken(token, createdAt, expiredAt, user);
    }
}
