package com.example.Elit17Plus_backend.models.dto;


import com.example.Elit17Plus_backend.models.entity.Benefit;
import com.example.Elit17Plus_backend.models.entity.Visit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO {

    private UUID id;
    private String egn; // ЕГН
    private String name;
    private String surname;
    private List<VisitDTO> visits = new ArrayList<>();
    private LocalDate lastVisitDate;
    private List<BenefitDTO> benefits = new ArrayList<>();
    private String image;
    @JsonIgnore
    private transient MultipartFile imageFile;
}
