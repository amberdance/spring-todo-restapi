package ru.hard2code.todoapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.hard2code.todoapi.model.TodoPriority;

import java.util.Optional;

public interface TodoPriorityRepository extends CrudRepository<TodoPriority, Long> {
    @Query(value = "SELECT tp.* FROM todo_priorities tp WHERE tp.priority = ?1", nativeQuery = true)
    Optional<TodoPriority> findByPriority(Integer priority);

}
