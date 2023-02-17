package ru.hard2code.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hard2code.todoapi.model.Todo;
import ru.hard2code.todoapi.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping
    public List<Todo> getAll() {
        return todoService.findAll();
    }

    @GetMapping("{id}")
    public Todo getById(@PathVariable long id) {
        return todoService.findById(id);
    }

    @PostMapping
    public Todo store(@RequestBody Todo todo) {
        return todoService.store(todo);
    }

    @PatchMapping("/{id}")
    public Todo update(@RequestBody Todo todo, @PathVariable Long id) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.deleteById(id);
    }

    @PostMapping("/complete")
    public Todo completeTodo(@RequestBody Todo todo) {
        return todoService.completeTodo(todo.getId(), todo.isDone());
    }


}
