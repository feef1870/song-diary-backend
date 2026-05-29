package com.example.chmuryProjekt.dto;

import java.util.List;

public record SongResponse(
        List<SongDto> content,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {}
