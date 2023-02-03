package ru.hard2code.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hard2code.todoapi.exception.ResourceNotFoundException;
import ru.hard2code.todoapi.model.TodoPriority;
import ru.hard2code.todoapi.repository.TodoPriorityRepository;

@Service
public class TodoPriorityService {
    @Autowired
    private TodoPriorityRepository todoPriorityRepository;

    public TodoPriority getDefaultPriority() {
        return findByPriority(TodoPriority.Priority.DEFAULT.value());
    }

    public TodoPriority findByPriority(int priority) {
        return todoPriorityRepository.findByPriority(TodoPriority.Priority.HIGH.value()).orElseThrow(ResourceNotFoundException::new);
    }
}
