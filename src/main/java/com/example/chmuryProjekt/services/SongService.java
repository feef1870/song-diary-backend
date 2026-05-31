package com.example.chmuryProjekt.services;

import com.example.chmuryProjekt.dto.*;
import com.example.chmuryProjekt.entities.Comment;
import com.example.chmuryProjekt.entities.Song;
import com.example.chmuryProjekt.repositories.CommentRepository;
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
    private final CommentRepository commentRepository;

    @Transactional
    public SongDto addSong(SongRequest request) {
        Song song = new Song();
        song.setName(request.name());
        song.setAlbum(request.album());
        song.setBand(request.band());

        Song savedSong = songRepository.save(song);

        return new SongDto(
                savedSong.getId(),
                savedSong.getBand(),
                savedSong.getAlbum(),
                savedSong.getName()
        );
    }

    @Transactional
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return songRepository.existsById(id);
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

    public SongDetailsDto getSongDetails(Long songId) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));

        List<CommentDto> commentDtos = song.getComments().stream()
                .map(c -> new CommentDto(c.getId(), c.getContent(), c.getCreatedAt()))
                .toList();

        return new SongDetailsDto(song.getId(), song.getName(), song.getAlbum(), song.getBand(), commentDtos);
    }

    public CommentDto addCommentToSong(Long songId, String content) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setSong(song);

        Comment savedComment = commentRepository.save(comment);

        return new CommentDto(savedComment.getId(), savedComment.getContent(), savedComment.getCreatedAt());
    }
}
