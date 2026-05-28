package com.example.chmuryProjekt.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Artist can not be blank")
    @Column(name = "band", nullable = false)
    private String band;

    @NotBlank(message = "Album can not be blank")
    @Column(name = "album", nullable = false)
    private String album;

    @NotBlank(message = "Name can not be blank")
    @Column(name = "name", nullable = false)
    private String name;
}
