package com.example.My_Todo_App.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.My_Todo_App.model.Todo;
import com.example.My_Todo_App.service.TodoService;

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
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/hello")
    public String getHello() {
        return todoService.getHello();
    }

    @GetMapping("/api/todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodo();
    }

    @GetMapping("/api/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> existingTodo = todoService.getTodoById(id);
        if(existingTodo.isPresent()){
            return ResponseEntity.ok(existingTodo.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/api/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodo(todo));
    }

    @PutMapping("/api/todos/{id}")
    public ResponseEntity<Todo> putTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Optional<Todo> existingTodo = todoService.putTodo(id, todo);
        if(existingTodo.isPresent()){
            return ResponseEntity.ok(existingTodo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        if(todoService.deleteTodo(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
