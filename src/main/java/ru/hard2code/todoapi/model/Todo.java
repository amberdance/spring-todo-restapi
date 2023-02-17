package ru.hard2code.todoapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @CreatedDate
    @CreationTimestamp
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "todo_priority_id")
    TodoPriority priority;

    String label;
    String description;
    boolean isDone;

    public Todo(long id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }
}
