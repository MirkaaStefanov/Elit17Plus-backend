package com.example.Elit17Plus_backend.repositories;

import com.example.Elit17Plus_backend.models.entity.Patient;
import com.example.Elit17Plus_backend.models.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByPatient(Patient patient);
}
