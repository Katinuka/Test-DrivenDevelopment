/**
 * This is code is from https://github.com/Katinuka/Test-DrivenDevelopment
 */
package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    @GetMapping
    public List<Book> showAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public Book showOneById(@PathVariable String id) {
        return bookService.getById(id);
    }

    @PostMapping
    public Book insert(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping
    public Book edit(@RequestBody Book book) {
        return bookService.update(book);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        bookService.delById(id);
    }

}
