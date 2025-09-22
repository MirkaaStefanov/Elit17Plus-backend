package com.example.Elit17Plus_backend.services.impl;

import com.example.Elit17Plus_backend.models.dto.BenefitDTO;
import com.example.Elit17Plus_backend.models.entity.Benefit;
import com.example.Elit17Plus_backend.repositories.BenefitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BenefitService {

    private final BenefitRepository benefitRepository;
    private final ModelMapper modelMapper;

    public BenefitDTO save(BenefitDTO benefitDTO) {
        Benefit benefit = modelMapper.map(benefitDTO, Benefit.class);
        Benefit saved = benefitRepository.save(benefit);
        return modelMapper.map(saved, BenefitDTO.class);
    }

    public BenefitDTO findById(UUID id) {
        return benefitRepository.findById(id)
                .map(benefit -> modelMapper.map(benefit, BenefitDTO.class))
                .orElse(null);
    }

    public void deleteById(UUID id) throws ChangeSetPersister.NotFoundException {
        Benefit benefit = benefitRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        benefit.setDeleted(true);
        benefitRepository.save(benefit);
    }

    public List<BenefitDTO> findAll() {
        return benefitRepository.findAllByDeletedFalse().stream()
                .map(benefit -> modelMapper.map(benefit, BenefitDTO.class))
                .collect(Collectors.toList());
    }

    public void update(UUID id, BenefitDTO benefitDTO) {
        Benefit benefit = modelMapper.map(benefitDTO, Benefit.class);
        benefit.setId(id);
        benefitRepository.save(benefit);
    }
}