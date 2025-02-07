package danik.may.repository;

import danik.may.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findById(int id);

    boolean existsById(int id);

    @Query(value = "SELECT EXISTS (SELECT * FROM tasks WHERE id=:taskId AND implementer=:userName)",
            nativeQuery = true)
    boolean isImplementer(@Param("taskId") int id, @Param("userName") String userName);

    @Query(value = "SELECT * FROM tasks WHERE implementer=:userName",
            nativeQuery = true)
    List<Task> findAllByImplementer(@Param("userName") String userName);

    @Query(value = "Update tasks" +
            "SET header = CASE WHEN h IS NOT NULL THEN h ELSE header END,\n" +
            "    description = CASE WHEN d IS NOT NULL THEN d ELSE description END,\n" +
            "    status = CASE WHEN p IS NOT NULL THEN p ELSE status END,\n" +
            "    priority = CASE WHEN s IS NOT NULL THEN s ELSE priority END,\n" +
            "    author = CASE WHEN a IS NOT NULL THEN a ELSE author END,\n" +
            "    implementer = CASE WHEN i IS NOT NULL THEN i ELSE  implementer END\n" +
            "WHERE id =:taskId",
            nativeQuery = true)
    void update(@Param("taskId")int id, @Param("h")String header, @Param("d")String description, @Param("p")String priority,
                @Param("s")String status, @Param("a")String author, @Param("i")String implementer);
}