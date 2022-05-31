package com.dining.diningapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review extends BaseEntity{

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private DiningUser user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    private Double peanutScore;
    private Double eggScore;
    private Double dairyScore;
    private String commentary;

    @Enumerated(value = EnumType.STRING)
    private ReviewStatus status;


}
