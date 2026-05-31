package com.example.chmuryProjekt.dto;

import jakarta.validation.constraints.NotBlank;

public record SongRequest(
        @NotBlank(message = "Name of the song is required") String name,
        @NotBlank(message = "Album of the song is required") String album,
        @NotBlank(message = "Atrinst is required") String band
) {
}
