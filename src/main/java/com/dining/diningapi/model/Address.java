package com.dining.diningapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address extends BaseEntity{


    private String city;
    private String state;

    @Column(unique = true)
    private String zipCode;
}
