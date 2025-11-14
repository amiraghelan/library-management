package com.librarymanagement.librarymanagement.mapper;

import com.librarymanagement.librarymanagement.dto.AuthorSummaryResponseDto;
import com.librarymanagement.librarymanagement.dto.BookResponseDto;
import com.librarymanagement.librarymanagement.dto.BookSummaryResponseDto;
import com.librarymanagement.librarymanagement.model.Book;

public final class BookMapper {

    private BookMapper() {}

    public static BookResponseDto toBookResponseDto(Book book) {
        // Create the nested author summary DTO
        AuthorSummaryResponseDto authorDto = new AuthorSummaryResponseDto(
                book.getAuthor().getId(),
                book.getAuthor().getName()
        );

        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                authorDto
        );
    }

    public static BookSummaryResponseDto toBookSummaryResponseDto(Book book) {
        return new BookSummaryResponseDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn()
        );
    }
}