package com.example.library.controller;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository repo;
 
    @GetMapping
    public List<Book> all() {
        return repo.findAll();
    }
 
    @PostMapping
    public Book add(@RequestBody Book b) {
        return repo.save(b);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
 
    @GetMapping("/search")
    public List<Book> search(@RequestParam String keyword) {
        return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            keyword, keyword, keyword);
    }
}
 
