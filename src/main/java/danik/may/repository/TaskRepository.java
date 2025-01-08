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
}