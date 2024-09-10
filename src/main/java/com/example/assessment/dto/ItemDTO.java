package com.example.assessment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
}

