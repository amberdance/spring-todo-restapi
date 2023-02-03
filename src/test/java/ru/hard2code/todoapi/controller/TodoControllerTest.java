package ru.hard2code.todoapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.hard2code.todoapi.exception.ResourceNotFoundException;
import ru.hard2code.todoapi.model.Todo;
import ru.hard2code.todoapi.service.TodoService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TodoService todoService;
    private Todo todo;


    @BeforeEach
    public void setup() {
        long id = 20L;
        todo = new Todo(id, "Todo1", "Description1", false);
        todoService.store(todo);
    }

    @Test
    public void shouldReturnTodo() throws Exception {
        long id = todo.getId();

        when(todoService.findById(id)).thenReturn(todo);
        mvc.perform(get("/todos/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpectAll(jsonPath("$.id").value(id), jsonPath("$.label").value(todo.getLabel()), jsonPath("$" + ".description").value(todo.getDescription()), jsonPath("$.done").value(todo.isDone()));
    }

    @Test
    public void shouldThrowResourceNotFoundException() throws Exception {
        long id = 978519L;

        mvc.perform(get("/todos/{id}", id)).andExpect(status().isNotFound());
    }

}