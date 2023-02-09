package com.soloproject.todo.controller;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.soloproject.todo.dto.TodoDto;
import com.soloproject.todo.entity.Todo;
import com.soloproject.todo.mapper.TodoMapper;
import com.soloproject.todo.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    void postTodoTest() throws Exception {
        //given
        TodoDto.Post post = new TodoDto.Post("운동하기", 1, false);
        TodoDto.Response response = new TodoDto.Response(1, "운동하기", 1, false);

        given(mapper.todoPostToTodo(Mockito.any(TodoDto.Post.class)))
            .willReturn(new Todo());
        given(todoService.createTodo(Mockito.any(Todo.class)))
            .willReturn(new Todo());
        given(mapper.todoToTodoResponse(Mockito.any(Todo.class)))
            .willReturn(response);

        String content = gson.toJson(post);

        //when
        ResultActions actions = mockMvc.perform(post("/")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(content)
        );

        //then
        actions
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void patchTodo() {
    }

    @Test
    void getTodo() {
    }

    @Test
    void getTodos() {
    }

    @Test
    void deleteTodo() {
    }

    @Test
    void deleteTodos() {
    }
}