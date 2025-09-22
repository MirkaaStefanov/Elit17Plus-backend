package com.example.Elit17Plus_backend.models.dto;

import com.example.Elit17Plus_backend.models.entity.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitDTO {

    private UUID id;
    private LocalDate visitDate;
    private PatientDTO patient;

}
