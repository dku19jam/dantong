package com.jaeun.dantong.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void addMember(GroupMember groupMember) {
        groupMembers.add(groupMember);
    }
}
