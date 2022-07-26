package com.jaeun.dantong.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "study_group")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;


    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private final Set<GroupMember> groupMembers = new HashSet<>();

    @Builder
    public Group(String groupName, GroupMember groupMember) {
        this.groupName = groupName;
        this.groupMembers.add(groupMember);
    }

    public void addMember(GroupMember groupMember) {
        groupMembers.add(groupMember);
    }
}
