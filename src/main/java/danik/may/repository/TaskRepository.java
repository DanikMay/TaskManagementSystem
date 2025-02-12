package danik.may.repository;

import danik.may.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Репо для работы с данными задач в таблице в БД
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    /**
     * Находит задачу по id
     *
     * @param id id задачи
     * @return данные задачи
     */
    Task findById(int id);

    /**
     * Проверяет, существует ли задача с заданным id
     *
     * @param id id задачи
     * @return результат проверки
     */
    boolean existsById(int id);

    /**
     * Проверяет по имени пользователя, является ли он исполнителем задачи с заданным id
     *
     * @param id       id задачи
     * @param userName имя пользователя
     * @return результат проверки
     */
    @Query(value = "SELECT EXISTS (SELECT * FROM tasks WHERE id=:taskId AND implementer=:userName)",
            nativeQuery = true)
    boolean isImplementer(@Param("taskId") int id, @Param("userName") String userName);

    /**
     * Достаёт список задач по имени пользователя-исполнителя
     *
     * @param userName - имя исполнителя
     * @return список найденных задач
     */
    @Query(value = "SELECT * FROM tasks WHERE implementer=:userName",
            nativeQuery = true)
    List<Task> findAllByImplementer(@Param("userName") String userName);

    /**
     * Обновляет поля задачи в БД полями из запроса, если новые поля не null
     *
     * @param id          id задачи
     * @param header      заголовок задачи
     * @param description описание задачи
     * @param priority    приоритет задачи
     * @param status      статус задачи
     * @param author      автор задачи
     * @param implementer исполнитель задачи
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE tasks " +
            "SET header = CASE WHEN :h IS NOT NULL THEN :h ELSE header END, " +
            "description = CASE WHEN :d IS NOT NULL THEN :d ELSE description END, " +
            "status = CASE WHEN :p IS NOT NULL THEN :p ELSE status END, " +
            "priority = CASE WHEN :s IS NOT NULL THEN :s ELSE priority END, " +
            "author = CASE WHEN :a IS NOT NULL THEN :a ELSE author END, " +
            "implementer = CASE WHEN :i IS NOT NULL THEN :i ELSE implementer END " +
            "WHERE id =:taskId",
            nativeQuery = true)
    void update(@Param("taskId") int id, @Param("h") String header, @Param("d") String description, @Param("p") String priority,
                @Param("s") String status, @Param("a") String author, @Param("i") String implementer);
}