package com.librarymanagement.librarymanagement.service.implementation;

import com.librarymanagement.librarymanagement.dto.AuthorResponseDto;
import com.librarymanagement.librarymanagement.dto.UpdateAuthorRequest;
import com.librarymanagement.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.librarymanagement.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;
import com.librarymanagement.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.librarymanagement.service.contract.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public AuthorResponseDto createAuthor(CreateAuthorRequest request) {
        Author newAuthor = Author.builder().name(request.name()).biography(request.biography()).build();

        Author savedAuthor = authorRepository.save(newAuthor);

        return AuthorMapper.toAuthorResponseDto(savedAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponseDto getAuthorById(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + authorId));
        return AuthorMapper.toAuthorResponseDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toAuthorResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthorResponseDto updateAuthor(Long authorId, UpdateAuthorRequest updateRequest) {
        Author existingAuthor = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + authorId));

        existingAuthor.setName(updateRequest.name());
        existingAuthor.setBiography(updateRequest.biography());

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return AuthorMapper.toAuthorResponseDto(updatedAuthor); // Use the static mapper
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
