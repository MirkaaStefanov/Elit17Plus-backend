package com.example.Elit17Plus_backend.services.impl;

import com.example.Elit17Plus_backend.models.dto.BenefitDTO;
import com.example.Elit17Plus_backend.models.dto.PatientDTO;
import com.example.Elit17Plus_backend.models.entity.Benefit;
import com.example.Elit17Plus_backend.models.entity.Patient;
import com.example.Elit17Plus_backend.repositories.BenefitRepository;
import com.example.Elit17Plus_backend.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final BenefitRepository benefitRepository;
    private final ModelMapper mapper;

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patient -> mapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    public PatientDTO findById(UUID id) throws ChangeSetPersister.NotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return mapper.map(patient, PatientDTO.class);
    }

    public PatientDTO save(PatientDTO dto) {
        Patient entity = mapper.map(dto, Patient.class);
        if (dto.getImage() != null) {
            entity.setImageData(Base64.getDecoder().decode(dto.getImage()));
        }
        return mapper.map(patientRepository.save(entity), PatientDTO.class);
    }

    public PatientDTO update(UUID id, PatientDTO dto) throws ChangeSetPersister.NotFoundException {
        Patient existing = patientRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);

        existing.setEgn(dto.getEgn());
        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setLastVisitDate(dto.getLastVisitDate());

        if (dto.getImage() != null) {
            existing.setImageData(Base64.getDecoder().decode(dto.getImage()));
        }

        // benefits
        if (dto.getBenefits() != null) {
            List<UUID> ids = dto.getBenefits().stream()
                    .map(BenefitDTO::getId)
                    .collect(Collectors.toList());
            List<Benefit> benefits = benefitRepository.findAllById(ids);
            existing.setBenefits(benefits);
        }

        return mapper.map(patientRepository.save(existing), PatientDTO.class);
    }

    public void delete(UUID id) throws ChangeSetPersister.NotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        patientRepository.delete(patient);
    }

}