package com.librarymanagement.librarymanagement.service.contract;

import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.dto.UpdateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(CreateAuthorRequest author);
    Author getAuthorById(Long authorId);
    List<Author> getAllAuthors();
    Author updateAuthor(Long authorId, UpdateAuthorRequest updateAuthorRequest);
    void deleteAuthor(Long authorId);
}
