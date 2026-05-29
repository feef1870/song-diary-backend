package com.example.chmuryProjekt.services;

import com.example.chmuryProjekt.dto.SongDto;
import com.example.chmuryProjekt.dto.SongResponse;
import com.example.chmuryProjekt.entities.Song;
import com.example.chmuryProjekt.repositories.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    @Transactional
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    public SongResponse findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Song> songsPage = songRepository.findAll(pageable);

        List<Song> listOfSongs = songsPage.getContent();

        List<SongDto> content = listOfSongs.stream()
                .map(song -> new SongDto(song.getId(), song.getBand(), song.getAlbum(), song.getName()))
                .toList();

        return new SongResponse(
                content,
                songsPage.getNumber(),
                songsPage.getSize(),
                songsPage.getTotalElements(),
                songsPage.getTotalPages(),
                songsPage.isLast()
        );
    }

    public List<Song> findByName(String name) {
        return songRepository.findByName(name);
    }
}
