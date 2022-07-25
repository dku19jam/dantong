package com.jaeun.dantong.service;

import com.jaeun.dantong.domain.dto.UserDto;
import com.jaeun.dantong.domain.entity.Group;
import com.jaeun.dantong.domain.entity.GroupMember;
import com.jaeun.dantong.domain.entity.User;
import com.jaeun.dantong.repository.GroupMemberRepository;
import com.jaeun.dantong.repository.GroupRepository;
import com.jaeun.dantong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Long createGroup(String email) {
        return null;
    }
}
