package com.example.Elit17Plus_backend.repositories;

import com.example.Elit17Plus_backend.models.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BenefitRepository extends JpaRepository<Benefit, UUID> {

    List<Benefit> findAllByDeletedFalse();

}
