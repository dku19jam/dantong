package com.jaeun.dantong.repository;

import com.jaeun.dantong.domain.entity.Group;
import com.jaeun.dantong.domain.entity.GroupMember;
import com.jaeun.dantong.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findMembersByGroupId(Long teamId);

    List<GroupMember> findWaitingMembersByGroupId(Long teamId);

    @Transactional
    @Modifying
    @Query("update GroupMember gm "+
            "set gm.memberRole = 'WAIT' where gm.user = :user "+
            "and gm.group = :group")
    void acceptMember(@Param("user") User user, @Param("group") Group group);

    @Transactional
    @Modifying
    @Query("select gm from GroupMember gm where gm.user= :user and gm.group= :group")
    GroupMember findGroupMemberByGroupAndUser(@Param("user") User user, @Param("group") Group group);

}
