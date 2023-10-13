package com.example.bookql.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookResolver {
    @Autowired
    private BookRepository bookRepository;

    public Iterable<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }
    public BookEntity createBook(String title, String author) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAuthor(author);
        bookRepository.save(book);
        return book;
    }

    public long countBooks() {
        return bookRepository.count();
    }
}
