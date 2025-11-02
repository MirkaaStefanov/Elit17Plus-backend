package com.example.Elit17Plus_backend.models.dto;

import com.example.Elit17Plus_backend.models.entity.Patient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDTO {

    private Long id;

    private PatientDTO patient;

    private String key;

    private Date uploadDate;

}
