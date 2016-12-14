package com.widehouse.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by kiel on 2016. 12. 14..
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    @EmbeddedId
    private MemberId memberId;

    private String name;
}
