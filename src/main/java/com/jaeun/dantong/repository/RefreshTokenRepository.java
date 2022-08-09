package com.jaeun.dantong.repository;

import com.jaeun.dantong.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("select t from RefreshToken t Where t.id= :id")
    String findRefreshTokenById(@Param("id") Long id);
}
