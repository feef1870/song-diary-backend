package com.example.chmuryProjekt.controllers;

import com.example.chmuryProjekt.entities.Song;
import com.example.chmuryProjekt.services.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public List<Song> findAll() {
        return songService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Song> addSong(@Valid @RequestBody Song song) {
        Song addedSong = songService.addSong(song);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedSong);
    }
}
