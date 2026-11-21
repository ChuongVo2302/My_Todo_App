package com.example.My_Todo_App.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.My_Todo_App.model.Todo;
import com.example.My_Todo_App.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello world!";
    }

    @GetMapping("/api/todos")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/api/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()){
            return ResponseEntity.ok(existingTodo.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/api/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        Todo newTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @PutMapping("/api/todos/{id}")
    public ResponseEntity<Todo> putTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()){
            Todo newTodo = existingTodo.get();
            newTodo.setTitle(todo.getTitle());
            newTodo.setDescription(todo.getDescription());
            newTodo.setStatus(todo.isStatus());

            Todo result = todoRepository.save(newTodo);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    } 
    

}
