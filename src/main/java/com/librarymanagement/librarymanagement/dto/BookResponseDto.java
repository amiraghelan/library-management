package com.librarymanagement.librarymanagement.dto;

public record BookResponseDto(
        Long id,
        String title,
        String isbn,
        AuthorSummaryResponseDto author
) {}
