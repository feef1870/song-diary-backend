package com.example.chmuryProjekt.controllers;

import com.example.chmuryProjekt.dto.*;
import com.example.chmuryProjekt.services.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public ResponseEntity<SongResponse> findAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return ResponseEntity.ok(songService.findAll(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDetailsDto> getSongDetails(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongDetails(id));
    }

    @PostMapping("/add")
    public ResponseEntity<SongDto> addSong(@Valid @RequestBody SongRequest request) {
        SongDto addedSong = songService.addSong(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedSong);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(
            @PathVariable Long id,
            @RequestBody Map<String, String> payload) {

        String content = payload.get("content");
        CommentDto savedComment = songService.addCommentToSong(id, content);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        if (!songService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}
