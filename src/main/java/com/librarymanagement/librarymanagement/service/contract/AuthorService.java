package com.librarymanagement.librarymanagement.service.contract;

import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;

public interface AuthorService {
    Author createAuthor(CreateAuthorRequest author);
    Author getAuthorById(Long authorId);
}
