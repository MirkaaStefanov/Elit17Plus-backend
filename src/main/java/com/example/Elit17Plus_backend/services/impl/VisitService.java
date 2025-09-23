package com.example.Elit17Plus_backend.services.impl;


import com.example.Elit17Plus_backend.exceptions.VisitException;
import com.example.Elit17Plus_backend.models.dto.PatientDTO;
import com.example.Elit17Plus_backend.models.dto.VisitDTO;
import com.example.Elit17Plus_backend.models.entity.Patient;
import com.example.Elit17Plus_backend.models.entity.Visit;
import com.example.Elit17Plus_backend.repositories.PatientRepository;
import com.example.Elit17Plus_backend.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public VisitDTO setVisit(UUID patientId) throws ChangeSetPersister.NotFoundException {
        Patient patient = patientRepository.findById(patientId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        Optional<Visit> optionalVisit = visitRepository.findByVisitDateAndPatient(LocalDate.now(), patient);
        if (optionalVisit.isPresent()){
            throw new VisitException("Пациентът вече е посещавал на тази дата");
        }

        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setVisitDate(LocalDate.now());

        // Добавяне на посещението към списъка
        patient.setLastVisitDate(visit.getVisitDate());

        // Запис на посещението (CascadeType.ALL може да го направи и автоматично при save на пациента)
        Visit savedVisit = visitRepository.save(visit);
        patientRepository.save(patient);

        return modelMapper.map(savedVisit, VisitDTO.class);
    }

    public List<VisitDTO> findByPatient(UUID patientId) throws ChangeSetPersister.NotFoundException {
        Patient patient = patientRepository.findById(patientId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        List<Visit> visits = visitRepository.findByPatient(patient);

        return visits.stream()
                .map(visit -> modelMapper.map(visit, VisitDTO.class))
                .collect(Collectors.toList());
    }
}
