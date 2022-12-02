package com.example.springbootmultipledb.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootmultipledb.model.book.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

}
