package ru.hard2code.todoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hard2code.todoapi.exception.ResourceNotFoundException;
import ru.hard2code.todoapi.model.Todo;
import ru.hard2code.todoapi.model.TodoPriority;
import ru.hard2code.todoapi.service.TodoService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TodoService todoService;

    private Todo todo;

    @BeforeEach
    public void setup() {
        todo = new Todo(1, "Todo1", "Description1", false, new TodoPriority(1, "High"));
    }

    @Test
    public void givenTodos_whenGetTodos_Then200StatusCodeAndTodosReturns() throws Exception {
        List<Todo> todos = new ArrayList<>() {{
            add(new Todo(1, "Todo1", "Description1"));
            add(new Todo(2, "Todo2", "Description2"));
            add(new Todo(3, "Todo3", "Description3"));
        }};

        when(todoService.findAll()).thenReturn(todos);
        mvc.perform(get("/todos")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(todos)));
    }


    @Test
    public void givenId_whenGetTodo_Then200StatusCodeAndTodoReturns() throws Exception {
        long id = todo.getId();

        when(todoService.findById(id)).thenReturn(todo);
        mvc.perform(get("/todos/{id}", id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpectAll(jsonPath("$.id").value(id),
                        jsonPath("$.label").value(todo.getLabel()),
                        jsonPath("$" + ".description").value(todo.getDescription()),
                        jsonPath("$.done").value(todo.isDone()));
    }


    @Test
    public void givenId_whenGetNotExistsTodo_thenResourceNotFoundExceptionThrows() throws Exception {
        long id = Mockito.anyLong();

        when(todoService.findById(id)).thenThrow(ResourceNotFoundException.class);
        mvc.perform(get("/todos/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    public void givenId_whenDeleteTodo_thenStatusCode200Returns() throws Exception {
        mvc.perform(delete("/todos/{id}", Mockito.anyLong())).andExpect(status().isOk());
    }

    @Test
    public void givenTodo_whenUpdate_then200StatusCodeAndTodoReturns() throws Exception {
        long id = todo.getId();
        String updLabel = "UpdatedLabel";
        String updDescription = "UpdatedDescription";

        todo.setLabel(updLabel);
        todo.setDescription(updDescription);

        when(todoService.update(id, todo)).thenReturn(todo);
        mvc.perform(patch("/todos/{id}", id)
                        .content(objectMapper.writeValueAsBytes(new Todo(todo.getId(), updLabel, updDescription)))
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$" + ".label").value(updLabel))
                .andExpect(jsonPath("$.description").value(updDescription));
    }

    @Test
    public void givenJsonString_then200StatusCodeAndTodoReturns() throws Exception {
        when(todoService.store(todo)).thenReturn(todo);
        mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(todo)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json((objectMapper.writeValueAsString(todo))));
    }
}