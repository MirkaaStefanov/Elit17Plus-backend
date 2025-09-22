package com.example.Elit17Plus_backend.repositories;

import com.example.Elit17Plus_backend.models.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {

}
