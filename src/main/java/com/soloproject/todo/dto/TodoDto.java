package com.soloproject.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TodoDto {
    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String title;

        @Range(min = 0)
        private Integer todo_order;

        @NotNull
        private Boolean completed;
    }

    @Getter
    public static class Patch {
        private long id;

        @NotBlank
        private String title;

        @Range(min = 0)
        private Integer todo_order;

        @NotNull
        private Boolean completed;

        public void setId(long id) {
            this.id = id;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long id;

        private String title;

        private Integer todo_order;

        private Boolean completed;
    }

}
