package ru.hard2code.todoapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "todo_priorities")
public class TodoPriority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    Long id;
    @Column
    @NonNull
    String label;
    @Column
    Priority priority;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TodoPriority that = (TodoPriority) o;
        return Objects.equals(id, that.id);
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
