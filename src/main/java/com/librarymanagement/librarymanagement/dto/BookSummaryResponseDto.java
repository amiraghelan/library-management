package com.librarymanagement.librarymanagement.dto;

public record BookSummaryResponseDto(
        Long id,
        String title,
        String isbn
) {}
