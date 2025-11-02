package com.example.Elit17Plus_backend.controllers;

import com.example.Elit17Plus_backend.models.dto.PatientDTO;
import com.example.Elit17Plus_backend.models.dto.VideoDTO;
import com.example.Elit17Plus_backend.models.entity.Patient;
import com.example.Elit17Plus_backend.models.entity.Video;
import com.example.Elit17Plus_backend.repositories.PatientRepository;
import com.example.Elit17Plus_backend.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videos")
public class VideoController {

    private final PatientRepository patientRepository;
    private final VideoRepository videoRepository;
    private final ModelMapper modelMapper;

    @PostMapping("/upload")
    public ResponseEntity<VideoDTO> uploadVideo(@RequestParam("patientId") UUID patientId, @RequestParam("key") String key, @RequestHeader("Authorization") String auth) throws Exception {
        try {

            Patient patient = patientRepository.findById(patientId).orElseThrow(ChangeSetPersister.NotFoundException::new);

            Video video = new Video();
            video.setPatient(patient);
            video.setKey(key);
            video.setUploadDate(LocalDate.now());

            return ResponseEntity.ok(modelMapper.map(videoRepository.save(video), VideoDTO.class));
        } catch (Exception e) {
            throw new Exception("Video was not uploaded");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<VideoDTO>> getAllForPatient(@PathVariable UUID id,  @RequestHeader("Authorization") String auth) throws ChangeSetPersister.NotFoundException {
        Patient patient = patientRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);


        return  ResponseEntity.ok(videoRepository.findByPatient(patient).stream()
                .map(video -> modelMapper.map(video, VideoDTO.class))
                .collect(Collectors.toList()));

    }

    /**
     * Gets a temporary URL to stream a video.
     * The 'key' would be fetched from your database for a specific video.
     */
    @GetMapping("/stream/{key}")
    public ResponseEntity<Map<String, String>> getVideoStreamUrl(@PathVariable String key, @RequestHeader("Authorization") String auth) {
        try {
            // TODO: Fetch this 'key' from your database first
//            String url = storageService.getPresignedVideoUrl(key);
//            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to get URL: " + e.getMessage()));
        }
        return null;
    }
}
