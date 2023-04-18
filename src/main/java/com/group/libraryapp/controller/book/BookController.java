package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.user.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    // 생성자를 통해 컨테이너로부터 스프링 빈을 주입받는다
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook() {
        bookService.saveBook();
    }
}
