package danik.may.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 *  Данные о задаче в системе управления задач, которые сохраняются в таблицу БД
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    /**
     * Id задачи
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq", allocationSize = 1)
    private int id;

    /**
     * Заголовок задачи
     */
    @Column(name = "header", nullable = false)
    private String header;

    /**
     * Описание задачи
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Статус задачи. Может быть "To Do", "Progress", "Done"
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * Приоритет задачи. Может быть "Low", "Mid", "High"
     */
    @Column(name = "priority", nullable = false)
    private String priority;

    /**
     * Username автора задачи
     */
    @Column(name = "author", nullable = false)
    private String author;

    /**
     * Username исполнителя задачи
     */
    @Column(name = "implementer", nullable = false)
    private String implementer;
}
