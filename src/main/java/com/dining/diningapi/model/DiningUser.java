package com.dining.diningapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DiningUser extends BaseEntity{

    @Column(unique = true)
    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String interest;

    @OneToMany
    @JoinColumn(name = "review_id")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

}
