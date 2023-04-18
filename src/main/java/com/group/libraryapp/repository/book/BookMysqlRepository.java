package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
// Primary : 의존성이 주입되는 우선권을 결정하는 어노테이션
@Primary
@Repository
public class BookMysqlRepository implements BookRepository {

    @Override
    public void saveBook() {
        // book.addBook(bookName);
    }

}
