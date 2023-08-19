package com.group.libraryapp.service.user.book;

import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.loanHistory.UserLoanHistory;
import com.group.libraryapp.domain.loanHistory.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;

    // 의존성 주입
    public BookService(UserRepository userRepository, BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 책 정보를 가져옴
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 대출 정보를 확인해 대출중인지 확인 -> 대출중이라면 예외 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("대출되어있는 책입니다");
        }

        // 유저정보를 가져온다
        // 유저 정보와 책 정보를 기반으로 대출 기록을 생성한다.

        User user = userRepository.findByName(request.getUserName());

        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName()));

    }
}
