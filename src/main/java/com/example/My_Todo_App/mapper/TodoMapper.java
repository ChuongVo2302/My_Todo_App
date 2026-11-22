package com.example.My_Todo_App.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.My_Todo_App.dto.TodoRequestDTO;
import com.example.My_Todo_App.dto.TodoResponseDTO;
import com.example.My_Todo_App.model.Todo;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    @Mapping(target = "id", ignore = true)
    Todo toEntity(TodoRequestDTO todoRequestDTO);

    TodoResponseDTO toResponse(Todo todo);
}