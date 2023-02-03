package ru.hard2code.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hard2code.todoapi.exception.ResourceNotFoundException;
import ru.hard2code.todoapi.model.Todo;
import ru.hard2code.todoapi.model.TodoPriority;
import ru.hard2code.todoapi.repository.TodoPriorityRepository;
import ru.hard2code.todoapi.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoPriorityService todoPriorityService;


    public List<Todo> findAll() {
        return todoRepository.findAll(Sort.by(Sort.Order.desc("id"), Sort.Order.desc("isDone")));
    }

    public Todo store(Todo todo) {
        if (todo.getPriority() == null) todo.setPriority(todoPriorityService.getDefaultPriority());
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
        return todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo completeTodo(long id, boolean state) {
        Todo todo = findById(id);
        todo.setDone(state);

        return todoRepository.save(todo);
    }
}
