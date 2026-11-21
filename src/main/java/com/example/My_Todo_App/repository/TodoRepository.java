package com.example.My_Todo_App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.My_Todo_App.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
