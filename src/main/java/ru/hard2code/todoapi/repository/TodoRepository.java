package ru.hard2code.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hard2code.todoapi.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
