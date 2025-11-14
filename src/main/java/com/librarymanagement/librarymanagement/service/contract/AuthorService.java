package com.librarymanagement.librarymanagement.service.contract;

import com.librarymanagement.librarymanagement.dto.AuthorResponseDto;
import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.dto.UpdateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto createAuthor(CreateAuthorRequest author);
    AuthorResponseDto getAuthorById(Long authorId);
    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto updateAuthor(Long authorId, UpdateAuthorRequest updateAuthorRequest);
    void deleteAuthor(Long authorId);
}
