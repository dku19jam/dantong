package com.jaeun.dantong.service;

import com.jaeun.dantong.domain.dto.request.CreateGroupRequest;
import com.jaeun.dantong.domain.entity.Group;
import com.jaeun.dantong.domain.entity.GroupMember;
import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.repository.GroupMemberRepository;
import com.jaeun.dantong.repository.GroupRepository;
import com.jaeun.dantong.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
class GroupServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @Test
    @Transactional
    void createGroupTest() {

        CreateGroupRequest request = new CreateGroupRequest();
        request.setGroupName("test1");

        User user = userRepository.findByEmail("email11").orElseThrow();
        Long groupId = groupService.createGroup("email11", request);
        GroupMember groupMember = GroupMember.createGroupMember(user, groupRepository.findById(1L).orElseThrow());
        groupMemberRepository.save(groupMember);

        Assertions.assertThat(groupRepository.findById(1L).orElseThrow().getGroupMembers().size() == 1);
    }

}