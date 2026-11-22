package com.example.My_Todo_App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.My_Todo_App.dto.TodoRequestDTO;
import com.example.My_Todo_App.dto.TodoResponseDTO;
import com.example.My_Todo_App.mapper.TodoMapper;
import com.example.My_Todo_App.model.Todo;
import com.example.My_Todo_App.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper; 

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper){
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public String getHello(){
        return "Hello world";
    }

    public List<TodoResponseDTO> getAllTodo(){
        return todoMapper.toResponseList(todoRepository.findAll());
    }

    public Optional<TodoResponseDTO> getTodoById(Long id){
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()){
            return Optional.of(todoMapper.toResponse(existingTodo.get()));
        }
        return Optional.empty();
    }

    public TodoResponseDTO addTodo(TodoRequestDTO todoRequestDTO){
        Todo todo = todoMapper.toEntity(todoRequestDTO);
        return todoMapper.toResponse(todoRepository.save(todo));
    }

    public Optional<TodoResponseDTO> putTodo(Long id, TodoRequestDTO todoRequestDTO){
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()){
            Todo foundTodo = existingTodo.get();

            foundTodo.setTitle(todoRequestDTO.title());
            foundTodo.setDescription(todoRequestDTO.description());
            foundTodo.setStatus(todoRequestDTO.status());
            todoRepository.save(foundTodo);

            return Optional.of(todoMapper.toResponse(foundTodo));
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
