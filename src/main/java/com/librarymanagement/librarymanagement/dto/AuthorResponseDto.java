package com.librarymanagement.librarymanagement.dto;

import java.util.List;

public record AuthorResponseDto(
        Long id,
        String name,
        String biography,
        List<BookSummaryResponseDto> books
) {}