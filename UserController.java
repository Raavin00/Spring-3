package com.example.library.controller;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repo;
 
    @GetMapping
    public List<User> all() {
        return repo.findAll();
    }
 
    @PostMapping
    public User add(@RequestBody User u) {
        return repo.save(u);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
