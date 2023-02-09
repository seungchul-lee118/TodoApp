package com.soloproject.todo.service;

import com.soloproject.todo.entity.Todo;
import com.soloproject.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void updateTodoTest() {
        //given
        Todo todo1 = new Todo("운동하기", 1, false);
        Todo todo2 = new Todo("공부하기", 2, true);

        BDDMockito.given(todoRepository.findById(Mockito.anyLong()))
            .willReturn(Optional.of(todo1));

        //when
        Todo updatedTodo = todoService.updateTodo(todo2);

        //then
        assertThat(updatedTodo.getTitle(), is(todo2.getTitle()));
     }
}