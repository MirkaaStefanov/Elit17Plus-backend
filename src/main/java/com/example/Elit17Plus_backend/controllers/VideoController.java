package com.example.Elit17Plus_backend.controllers;

import com.example.Elit17Plus_backend.services.impl.R2StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videos")
public class VideoController {

    private final R2StorageService storageService;


    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            String key = storageService.uploadVideo(file);
            // TODO: Save the 'key' in your database (e.g., video.setR2Key(key);)
            return ResponseEntity.ok(Map.of("key", key, "message", "Video uploaded successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Upload failed: " + e.getMessage()));
        }
    }

    /**
     * Gets a temporary URL to stream a video.
     * The 'key' would be fetched from your database for a specific video.
     */
    @GetMapping("/stream/{key}")
    public ResponseEntity<Map<String, String>> getVideoStreamUrl(@PathVariable String key) {
        try {
            // TODO: Fetch this 'key' from your database first
            String url = storageService.getPresignedVideoUrl(key);
            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to get URL: " + e.getMessage()));
        }
    }
}
