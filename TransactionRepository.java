package com.example.library.repository;
import com.example.library.model.Transaction;
import com.example.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
 
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByReturnDateBeforeAndStatus(LocalDate date, String status);
}
