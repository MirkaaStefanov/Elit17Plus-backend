package com.example.Elit17Plus_backend.models.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BenefitDTO {

    private UUID id;
    private String name;
    private String description;
    private boolean deleted = false;
}
