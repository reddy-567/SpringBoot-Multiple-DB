package com.example.springbootmultipledb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootmultipledb.book.repository.BookRepository;
import com.example.springbootmultipledb.model.book.Book;
import com.example.springbootmultipledb.model.user.User;
import com.example.springbootmultipledb.user.repository.UserRepository;

@RestController
public class MyController {
	
	@Autowired
	private BookRepository b_repo;
	@Autowired
	private UserRepository u_repo;
	
	@PostMapping("/Book")
	public Book addBook(@RequestBody Book book)
	{
		return b_repo.save(book);
	}
	
	@GetMapping("/Books")
	public List<Book> getBooks()
	{
		return b_repo.findAll();
	}
	
	@GetMapping("/Book/{id}")
	public Book getBook(@PathVariable("id") int id)
	{
		return b_repo.findById(id).orElse(null);
	}
	
	
	@PostMapping("/User")
	public User addUser(@RequestBody User user)
	{
		return u_repo.save(user);
	}
	
	@GetMapping("/Users")
	public List<User> getUsers()
	{
		return u_repo.findAll();
	}
	
	@GetMapping("/User/{id}")
	public User getUser(@PathVariable("id") int id)
	{
		return u_repo.findById(id).orElse(null);
	}

}
