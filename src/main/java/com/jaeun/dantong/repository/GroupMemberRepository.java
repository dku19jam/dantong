package com.jaeun.dantong.repository;

import com.jaeun.dantong.domain.entity.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    List<GroupMember> findMembersByGroupId(Long teamId);
}
