package com.jaeun.dantong.service;


import com.jaeun.dantong.domain.dto.request.AcceptGroupRequest;
import com.jaeun.dantong.domain.dto.request.CreateGroupRequest;
import com.jaeun.dantong.domain.dto.request.JoinGroupRequest;
import com.jaeun.dantong.domain.entity.Group;
import com.jaeun.dantong.domain.entity.GroupMember;
import com.jaeun.dantong.domain.entity.MemberRole;
import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.exception.NoRightToAcceptException;
import com.jaeun.dantong.repository.GroupMemberRepository;
import com.jaeun.dantong.repository.GroupRepository;
import com.jaeun.dantong.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;


    @Transactional
    public Long createGroup(String email, CreateGroupRequest request) {
        User user = getUserByEmail(email);
        Group group = groupRepository.save(new Group(request.getGroupName()));
        GroupMember leader = GroupMember.createGroupMember(user, group);
        leader.setMemberRole(MemberRole.LEADER);

        return group.getId();
    }

    @Transactional
    public void joinGroup(String email, JoinGroupRequest request) {
        //TODO  return value 정해주기
        User user = getUserByEmail(email);
        Group group = getGroupById(request.getGroupId());
        GroupMember newMember = GroupMember.createGroupMember(user, group);
        newMember.setMemberRole(MemberRole.WAIT);

        group.addMember(newMember);
    }

    @Transactional
    public void acceptMember(String email, AcceptGroupRequest request) throws NoRightToAcceptException {

        User leader = userRepository.findByEmail(email).orElseThrow();
        Group group = getGroupById(request.getGroupId());
        GroupMember leaderMember = groupMemberRepository.findGroupMemberByGroupAndUser(leader, group);

        if (!isLeader(leaderMember)) {
            throw new NoRightToAcceptException();
        }

        User invitedUser = userRepository.findById(request.getMemberId()).orElseThrow();
        GroupMember invitedGroupMember = GroupMember.createGroupMember(invitedUser, group);
        groupMemberRepository.acceptMember(invitedUser, group);
    }

    private boolean isLeader(GroupMember groupMember) {
        return groupMember.getMemberRole() == MemberRole.LEADER;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    private GroupMember getGroupMemberById(Long id) {
        return groupMemberRepository.findById(id).orElseThrow();
    }
    private Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow();
    }
}
