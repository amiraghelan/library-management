package com.librarymanagement.librarymanagement.controller;

import com.librarymanagement.librarymanagement.dto.CreateAuthorRequest;
import com.librarymanagement.librarymanagement.model.Author;
import com.librarymanagement.librarymanagement.service.contract.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody CreateAuthorRequest request) {
        System.out.println("===============hreklsdjskgjfsklgj");
        Author createdAuthor = authorService.createAuthor(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAuthor.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author placeholderAuthor = Author.builder().id(id).name("Placeholder").build();
        return ResponseEntity.ok(placeholderAuthor);
    }
}