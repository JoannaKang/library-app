package com.group.libraryapp.domain.loanHistory;

import org.springframework.data.jpa.repository.JpaRepository;

// <매핑시키는 모델이름, id타입>
public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    // select * from user_loan_history where by book_name / isReturn
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);
}
