package com.jaeun.dantong.repository;

import com.jaeun.dantong.domain.entity.Group;
import com.jaeun.dantong.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findAllByGroupName(User user);

    Group findByGroupName(String name);
}
