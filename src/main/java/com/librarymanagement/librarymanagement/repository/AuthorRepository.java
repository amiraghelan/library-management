package com.librarymanagement.librarymanagement.repository;

import com.librarymanagement.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
