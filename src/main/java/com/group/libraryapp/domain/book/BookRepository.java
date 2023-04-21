package com.group.libraryapp.domain.book;

import com.group.libraryapp.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
