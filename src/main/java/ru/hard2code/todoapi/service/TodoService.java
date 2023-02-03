package ru.hard2code.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hard2code.todoapi.exception.ResourceNotFoundException;
import ru.hard2code.todoapi.model.Todo;
import ru.hard2code.todoapi.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo store(Todo todo) {
        return todoRepository.save(todo);
    }

    // TODO: 02.02.2023  partial update https://www.baeldung.com/spring-data-partial-update
    public Todo update(Long id, Todo t) {
        Todo todo = findById(id);
        todo.setDone(t.isDone());
        //todo.setDescription(t.getDescription());
        //todo.setLabel(t.getLabel());

        return todoRepository.save(todo);
    }

    public Todo findById(Long id) {
        return todoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
