package com.librarymanagement.librarymanagement.service.implementation;

import com.librarymanagement.librarymanagement.dto.BookResponseDto;
import com.librarymanagement.librarymanagement.dto.CreateBookRequest;
import com.librarymanagement.librarymanagement.dto.UpdateBookRequest;
import com.librarymanagement.librarymanagement.exception.ResourceNotFoundException;
import com.librarymanagement.librarymanagement.mapper.BookMapper;
import com.librarymanagement.librarymanagement.model.Author;
import com.librarymanagement.librarymanagement.model.Book;
import com.librarymanagement.librarymanagement.repository.AuthorRepository;
import com.librarymanagement.librarymanagement.repository.BookRepository;
import com.librarymanagement.librarymanagement.service.contract.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public BookResponseDto createBook(CreateBookRequest request) {
        Author author = authorRepository.findById(request.authorId()).orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + request.authorId()));

        bookRepository.findByIsbn(request.isbn()).ifPresent(book -> {
            throw new IllegalArgumentException("Book with ISBN " + request.isbn() + " already exists.");
        });

        Book newBook = Book.builder().title(request.title()).isbn(request.isbn()).author(author).build();
        Book savedBook = bookRepository.save(newBook);
        return BookMapper.toBookResponseDto(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toBookResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        return BookMapper.toBookResponseDto(book);
    }

    @Override
    @Transactional
    public BookResponseDto updateBook(Long bookId, UpdateBookRequest request) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        Optional<Book> conflictingBook = bookRepository.findByIsbn(request.isbn());
        if (conflictingBook.isPresent() && !conflictingBook.get().getId().equals(bookId)) {
            throw new IllegalArgumentException("Another book with ISBN " + request.isbn() + " already exists.");
        }

        existingBook.setTitle(request.title());
        existingBook.setIsbn(request.isbn());

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toBookResponseDto(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book not found with ID: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }
}