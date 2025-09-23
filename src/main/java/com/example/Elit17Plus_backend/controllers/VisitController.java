package com.example.Elit17Plus_backend.controllers;

import com.example.Elit17Plus_backend.models.dto.VisitDTO;
import com.example.Elit17Plus_backend.services.impl.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/{patientId}")
    public ResponseEntity<VisitDTO> addVisit(@PathVariable UUID patientId, @RequestHeader("Authorization") String auth) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(visitService.setVisit(patientId));
    }

    @GetMapping("/getForPatient/{patientId}")
    public ResponseEntity<List<VisitDTO>> getForPatient(@PathVariable UUID patientId, @RequestHeader("Authorization") String auth) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(visitService.findByPatient(patientId));
    }

}
