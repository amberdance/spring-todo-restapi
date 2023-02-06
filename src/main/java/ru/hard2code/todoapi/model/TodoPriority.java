package ru.hard2code.todoapi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "todo_priorities")
public class TodoPriority {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String label;
    @Column(nullable = false)
    private Priority priority;

    public TodoPriority() {
    }

    public TodoPriority(long id, String label) {
        this.id = id;
        this.label = label;
        this.priority = Priority.DEFAULT;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoPriority that = (TodoPriority) o;
        return id == that.id && Objects.equals(label, that.label);
    }

    @Override
    public String toString() {
        return "TodoPriority{" + "id=" + id + ", label='" + label + '\'' + '}';
    }

    public enum Priority {
        HIGH(1), DEFAULT(2), LOW(3);

        private final int index;

        Priority(int i) {
            index = i;
        }

        public int value() {
            return index;
        }
    }
}
