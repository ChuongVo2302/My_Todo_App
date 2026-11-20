package com.example.My_Todo_App.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.My_Todo_App.model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class TodoController {
    private List<Todo> todoList = new ArrayList<>();
    private long idCounter = 0L;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello world!";
    }

    @GetMapping("/api/todos")
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @GetMapping("/api/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
        Optional<Todo> result = findTodoById(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/api/todos")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        idCounter++;
        todo.setId(idCounter);
        todoList.add(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @PutMapping("/api/todos/{id}")
    public ResponseEntity<Todo> putTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Optional<Todo> result = findTodoById(id);
        if(result.isPresent()){
            Todo existingTodo = result.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setStatus(todo.getStatus());

            return ResponseEntity.ok(existingTodo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        Optional<Todo> result = findTodoById(id);
        if(result.isPresent()){
            todoList.remove(result.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    } 

        private Optional<Todo> findTodoById(Long id){
        Optional<Todo> result = todoList.stream().filter(todo -> todo.getId().equals(id)).findFirst();
        return result;
    }
    

}
