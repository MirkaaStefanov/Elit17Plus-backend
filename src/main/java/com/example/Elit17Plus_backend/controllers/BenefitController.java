package com.example.Elit17Plus_backend.controllers;

import com.example.Elit17Plus_backend.models.dto.BenefitDTO;
import com.example.Elit17Plus_backend.services.impl.BenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/benefits")
@RequiredArgsConstructor
public class BenefitController {

    private final BenefitService benefitService;

    @GetMapping
    public ResponseEntity<List<BenefitDTO>> getAllBenefits(@RequestHeader(value = "Authorization", required = false) String auth) {
        return ResponseEntity.ok(benefitService.findAll());
    }

    @GetMapping("/byId")
    public ResponseEntity<BenefitDTO> getBenefitById(@RequestParam("id") UUID id, @RequestHeader(value = "Authorization", required = false) String auth) {
        BenefitDTO benefit = benefitService.findById(id);
        if (benefit != null) {
            return ResponseEntity.ok(benefit);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createBenefit(@RequestBody BenefitDTO benefitDTO, @RequestHeader("Authorization") String auth) {
        benefitService.save(benefitDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateBenefit(@PathVariable UUID id,  @RequestBody BenefitDTO benefitDTO, @RequestHeader("Authorization") String auth) {
        benefitService.update(id, benefitDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteBenefit(@RequestParam("id") UUID id, @RequestHeader("Authorization") String auth) throws ChangeSetPersister.NotFoundException {
        benefitService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
