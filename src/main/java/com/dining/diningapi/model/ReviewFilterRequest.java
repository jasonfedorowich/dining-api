package com.dining.diningapi.model;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ReviewFilterRequest {

    @Enumerated(value = EnumType.STRING)
    private ReviewStatus status;
}
