package ru.hard2code.todoapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreatedDate
    @CreationTimestamp
    private Timestamp createdAt;
    private String label;
    private String description;
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "todo_priority_id")
    private TodoPriority priority;


    public Todo() {
    }


    public Todo(long id, String label, String description, boolean isDone, TodoPriority priority) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public TodoPriority getPriority() {
        return priority;
    }

    public void setPriority(TodoPriority priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, isDone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && isDone == todo.isDone && label.equals(todo.label) && Objects.equals(description,
                todo.description);
    }

    @Override
    public String toString() {
        return "Todo{" + "id=" + id + ", label='" + label + '\'' + ", description='" + description + '\'' + ", isDone"
                + "=" + isDone + '}';
    }
}
