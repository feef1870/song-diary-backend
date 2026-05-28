package com.example.chmuryProjekt.services;

import com.example.chmuryProjekt.entities.Song;
import com.example.chmuryProjekt.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public List<Song> findByName(String name) {
        return songRepository.findByName(name);
    }
}
