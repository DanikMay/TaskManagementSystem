package danik.may.repository;

import danik.may.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class TaskRepositoryTest {
    @Autowired
    public TaskRepository repo;

    @Test
    void isImplementer() {
        boolean result = repo.isImplementer(6, "someUser");
        assertTrue(result);
    }

    @Test
    void getAll() {
        List<Task> taskList = repo.findAllByImplementer("someUser");
        System.out.println(taskList.get(0).getAuthor());
        assertNull(taskList);
    }
}
