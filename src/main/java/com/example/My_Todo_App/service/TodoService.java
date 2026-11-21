package com.example.My_Todo_App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.My_Todo_App.model.Todo;
import com.example.My_Todo_App.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public String getHello(){
        return "Hello world";
    }

    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id){
        return todoRepository.findById(id);
    }

    public Todo addTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public Optional<Todo> putTodo(Long id, Todo todo){
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()){
            Todo newTodo = existingTodo.get();

            newTodo.setTitle(todo.getTitle());
            newTodo.setDescription(todo.getDescription());
            newTodo.setStatus(todo.isStatus());
            todoRepository.save(newTodo);

            return Optional.of(newTodo);
        }

        return Optional.empty();
    }

    public boolean deleteTodo(Long id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
