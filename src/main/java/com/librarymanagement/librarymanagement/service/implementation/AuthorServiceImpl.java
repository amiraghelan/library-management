package com.librarymanagement.librarymanagement.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;
import com.librarymanagement.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.librarymanagement.service.contract.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public Author createAuthor(CreateAuthorRequest request) {
        Author newAuthor = Author.builder().
                name(request.name()).
                biography(request.biography()).
                build();

        return authorRepository.save(newAuthor);
    }
}
