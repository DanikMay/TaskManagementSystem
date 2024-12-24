package danik.may.service;

import danik.may.entity.Role;
import danik.may.entity.Task;
import danik.may.entity.User;
import danik.may.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    private TaskRepository repository = mock(TaskRepository.class);
    private UserService userService = mock(UserService.class);

    private TaskService taskService = new TaskService(repository);

    @Test
    void getForSimpleUser() {
        User user = User.builder()
                .username("alex")
                .role(Role.ROLE_USER).build();
        Task task = Task.builder().id(1).executor("danik").build();

        when(repository.findById(1)).thenReturn(task);
        when(userService.getCurrentUser()).thenReturn(user);
        boolean exceptionThrown = false;

        try {
            taskService.get(1);
        } catch (RuntimeException ex) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }
    @Test
    void getForExecutorUser()  {
        User user = User.builder()
                .username("alex")
                .role(Role.ROLE_USER).build();
        Task task = Task.builder().id(1).executor("danik").build();

        when(repository.findById(1)).thenReturn(task);
        when(userService.getCurrentUser()).thenReturn(user);
        taskService.get(1);
    }
    @Test
    void getForAdmin() {
        User user = User.builder()
                .username("danik")
                .role(Role.ROLE_ADMIN).build();
        Task task = Task.builder().id(1).executor("alex").build();

        when(repository.findById(1)).thenReturn(task);
        when(userService.getCurrentUser()).thenReturn(user);
        taskService.get(1);
    }
}