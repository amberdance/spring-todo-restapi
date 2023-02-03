package ru.hard2code.todoapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String label;
    private String description;
    private boolean isDone;

    public Todo() {
    }

    public Todo(long id, String label, String description, boolean isDone) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.isDone = isDone;
    }

    public Todo(String label, String description) {
        Objects.requireNonNull(label);
        this.label = label;
        this.description = description;
        this.isDone = false;
    }

    public Todo(String label) {
        Objects.requireNonNull(label);
        this.label = label;
        this.isDone = false;
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && isDone == todo.isDone && label.equals(todo.label) && Objects.equals(description,
                todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, isDone);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}
