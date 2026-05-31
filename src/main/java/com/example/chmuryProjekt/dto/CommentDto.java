package com.example.chmuryProjekt.dto;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        String content,
        LocalDateTime createdAt
) {
}
