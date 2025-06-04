package com.example.library.controller;
import com.example.library.model.*;
import com.example.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
 
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired private TransactionRepository trRepo;
    @Autowired private BookRepository bookRepo;
    @Autowired private UserRepository userRepo;
 
    @PostMapping("/lend")
    public String lendBook(@RequestParam Long bookId, @RequestParam Long userId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (!book.isAvailability()) return "Book is not available.";
 
        Transaction tx = new Transaction();
        tx.setBook(book);
        tx.setUser(userRepo.findById(userId).orElseThrow());
        tx.setIssueDate(LocalDate.now());
        tx.setStatus("ISSUED");
        book.setAvailability(false);
 
        trRepo.save(tx);
        bookRepo.save(book);
        return "Book issued.";
    }
 
    @PostMapping("/return")
    public String returnBook(@RequestParam Long transactionId) {
        Transaction tx = trRepo.findById(transactionId).orElseThrow();
        tx.setReturnDate(LocalDate.now());
        tx.setStatus("RETURNED");
 
        Book book = tx.getBook();
        book.setAvailability(true);
 
        trRepo.save(tx);
        bookRepo.save(book);
        return "Book returned.";
    }
 
    @GetMapping("/overdue")
    public List<Transaction> overdue() {
        return trRepo.findByReturnDateBeforeAndStatus(LocalDate.now(), "ISSUED");
    }
 
    @GetMapping("/user/{userId}")
    public List<Transaction> userHistory(@PathVariable Long userId) {
        return trRepo.findByUser(userRepo.findById(userId).orElseThrow());
    }
}
