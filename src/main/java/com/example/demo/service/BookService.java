package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.domain.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // 1) 도서 등록
    public Book createBook(Long userId, Book book) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        book.setUser(user); // 책 등록시 user 설정
        return bookRepository.save(book);
    }

    // 2) 도서 전체 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 3) 도서 상세 조회
    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("책이 존재하지 않습니다."));
    }

    // 4) 도서 삭제
    public void deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
    }

    // 5) 도서 업데이트
    public Book updateBook(Long bookId, Book newBook) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("책이 존재하지 않습니다."));

        book.setTitle(newBook.getTitle());
        book.setContent(newBook.getContent());
        book.setCoverImageUrl(newBook.getCoverImageUrl());
        book.setCategory(newBook.getCategory());

        return book;
    }

}