package com.librarymanagement.librarymanagement.service.implementation;

import com.librarymanagement.librarymanagement.dto.UpdateAuthorRequest;
import com.librarymanagement.librarymanagement.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;
import com.librarymanagement.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.librarymanagement.service.contract.AuthorService;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + authorId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author updateAuthor(Long authorId, UpdateAuthorRequest updateRequest) {
        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + authorId));

        existingAuthor.setName(updateRequest.name());
        existingAuthor.setBiography(updateRequest.biography());

        return authorRepository.save(existingAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author not found with ID: " + authorId);
        }
        authorRepository.deleteById(authorId);
    }
}
