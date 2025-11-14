package com.librarymanagement.librarymanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateBookRequest(
        @NotBlank(message = "Title cannot be blank")
        @Size(max = 255, message = "Title cannot exceed 255 characters")
        String title,

        @NotBlank(message = "ISBN cannot be blank")
        @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
        String isbn,

        @NotNull(message = "Author ID cannot be null")
        Long authorId
) {}
