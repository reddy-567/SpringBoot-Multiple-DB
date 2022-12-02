package com.example.springbootmultipledb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootmultipledb.model.user.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
