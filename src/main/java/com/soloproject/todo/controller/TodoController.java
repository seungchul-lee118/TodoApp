package com.soloproject.todo.controller;

import com.soloproject.todo.dto.TodoDto;
import com.soloproject.todo.entity.Todo;
import com.soloproject.todo.mapper.TodoMapper;
import com.soloproject.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    @PostMapping("/")
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post requestBody) {
        Todo todo = mapper.todoPostToTodo(requestBody);
        Todo createdTodo = todoService.createTodo(todo);

        return new ResponseEntity(mapper.todoToTodoResponse(createdTodo),
            HttpStatus.CREATED);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") long todoId,
                                    @Valid @RequestBody TodoDto.Patch requestBody) {
        requestBody.setId(todoId);

        Todo todo = mapper.todoPatchToTodo(requestBody);
        Todo updatedTodo = todoService.updateTodo(todo);

        return ResponseEntity.ok(mapper.todoToTodoResponse(updatedTodo));
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") long todoId) {
        Todo findTodo = todoService.findTodo(todoId);
        return ResponseEntity.ok(mapper.todoToTodoResponse(findTodo));
    }

    @GetMapping("/")
    public ResponseEntity getTodos() {
        List<Todo> todos = todoService.findTodos();
        return ResponseEntity.ok(mapper.todosToTodoResponses(todos));
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    public ResponseEntity deleteTodos() {
        todoService.deleteTodos();
        return ResponseEntity.noContent().build();
    }
}
