package com.example.Elit17Plus_backend.repositories;

import com.example.Elit17Plus_backend.models.entity.Patient;
import com.example.Elit17Plus_backend.models.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {

    List<Visit> findByPatient(Patient patient);

    Optional<Visit> findByVisitDateAndPatient(LocalDate visitDate, Patient patient);
}
