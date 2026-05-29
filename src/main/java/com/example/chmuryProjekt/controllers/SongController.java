package com.example.chmuryProjekt.controllers;

import com.example.chmuryProjekt.dto.SongResponse;
import com.example.chmuryProjekt.entities.Song;
import com.example.chmuryProjekt.services.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/add")
    public ResponseEntity<Song> addSong(@Valid @RequestBody Song song) {
        Song addedSong = songService.addSong(song);

        return ResponseEntity.status(HttpStatus.CREATED).body(addedSong);
    }
}
