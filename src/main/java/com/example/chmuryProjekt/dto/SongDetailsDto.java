package com.example.chmuryProjekt.dto;

import java.util.List;

public record SongDetailsDto(
        Long id,
        String name,
        String album,
        String band,
        List<CommentDto> comments
) {
}
