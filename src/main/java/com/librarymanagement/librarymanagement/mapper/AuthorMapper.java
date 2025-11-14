package com.librarymanagement.librarymanagement.mapper;

import com.librarymanagement.librarymanagement.dto.AuthorResponseDto;
import com.librarymanagement.librarymanagement.dto.BookSummaryResponseDto;
import com.librarymanagement.librarymanagement.model.Author;

import java.util.List;
import java.util.stream.Collectors;

public final class AuthorMapper {

    private AuthorMapper() {}

    public static AuthorResponseDto toAuthorResponseDto(Author author) {
        List<BookSummaryResponseDto> bookSummaries = author.getBooks().stream()
                .map(BookMapper::toBookSummaryResponseDto)
                .collect(Collectors.toList());

        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getBiography(),
                bookSummaries
        );
    }
}