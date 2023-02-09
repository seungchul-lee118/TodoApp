package com.soloproject.todo.service;

import com.soloproject.todo.entity.Todo;
import com.soloproject.todo.exception.BusinessLogicException;
import com.soloproject.todo.exception.ExceptionCode;
import com.soloproject.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTitle())
            .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
            .ifPresent(order -> findTodo.setTodoOrder(order));
        Optional.ofNullable(todo.getCompleted())
            .ifPresent(completed -> findTodo.setCompleted(completed));

        return findTodo;
    }

    public Todo findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll(Sort.by("id").ascending());
    }

    public void deleteTodo(long todoId) {
        Todo findTodo = findVerifiedTodo(todoId);
        todoRepository.delete(findTodo);
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    private Todo findVerifiedTodo(long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        Todo findTodo = optionalTodo
            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_EXIST));
        return findTodo;
    }

}
