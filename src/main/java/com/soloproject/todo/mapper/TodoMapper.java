package com.soloproject.todo.mapper;

import com.soloproject.todo.dto.TodoDto;
import com.soloproject.todo.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mapping(source = "todo_order", target = "todoOrder")
    Todo todoPostToTodo(TodoDto.Post requestBody);

    @Mapping(source = "todo_order", target = "todoOrder")
    Todo todoPatchToTodo(TodoDto.Patch requestBody);

    @Mapping(source = "todoOrder", target = "todo_order")
    TodoDto.Response todoToTodoResponse(Todo todo);

    List<TodoDto.Response> todosToTodoResponses(List<Todo> todos);
}
