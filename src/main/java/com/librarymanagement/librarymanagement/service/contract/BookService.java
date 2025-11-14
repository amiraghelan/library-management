package com.librarymanagement.librarymanagement.service.contract;

import com.librarymanagement.librarymanagement.dto.BookResponseDto;
import com.librarymanagement.librarymanagement.dto.CreateBookRequest;
import com.librarymanagement.librarymanagement.dto.UpdateBookRequest;
import com.librarymanagement.librarymanagement.model.Book;

import java.util.List;

public interface BookService {
    BookResponseDto createBook(CreateBookRequest request);

    List<BookResponseDto> getAllBooks();

    BookResponseDto getBookById(Long bookId);

    BookResponseDto updateBook(Long bookId, UpdateBookRequest request);

    void deleteBook(Long bookId);
}