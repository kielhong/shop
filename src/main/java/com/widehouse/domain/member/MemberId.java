package com.widehouse.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by kiel on 2016. 12. 12..
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberId implements Serializable {
    @Column(length = 20)
    private String id;
}
