package danik.may.repository;

import danik.may.app.SpringBootApp;
import danik.may.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBootApp.class)
public class TaskRepositoryTest {
    @Autowired
    public TaskRepository repo;

    @Test
    void isImplementer() {
        boolean result = repo.isImplementer(6, "danik");
        assertTrue(result);
    }

    @Test
    void getAll() {
        List<Task> taskList = repo.findAllByImplementer("someUser");
        System.out.println(taskList.get(0).getAuthor());
        assertNotNull(taskList);
    }

    @Test
    void update() {
        Task task = Task.builder().id(7).header("header1").description("description1").priority("high").status("ToDo").author("danikMay").implementer("danikMay").build();
        repo.update(7, task.getHeader(), task.getDescription(), task.getPriority(), task.getStatus(), task.getAuthor(), task.getImplementer());
    }
}