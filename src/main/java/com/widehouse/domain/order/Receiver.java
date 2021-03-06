package com.widehouse.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by kiel on 2016. 12. 7..
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Receiver {
    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_phone")
    private String phone;
}
