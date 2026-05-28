package com.example.chmuryProjekt.repositories;

import com.example.chmuryProjekt.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {
    List<Song> findByName(String name);
}
