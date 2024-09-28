/**
 * This is code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final List<Book> books = new ArrayList<>();
    {
        books.add(new Book("1", "name1", "author1", "000001","description1"));
        books.add(new Book("2", "name2", "author2", "000002","description3"));
        books.add(new Book("3", "name3", "author1", "000003","description3"));
    }

    // Remember to comment it :)
    @PostConstruct
    void init() {
        bookRepository.deleteAll();
        bookRepository.saveAll(books);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book create(Book books) {
        return bookRepository.save(books);
    }

    public Book update(Book books) {
        return bookRepository.save(books);
    }

    public void delById(String id) {
        bookRepository.deleteById(id);
    }

}
