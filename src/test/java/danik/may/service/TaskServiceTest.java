package danik.may.service;

import danik.may.dto.request.TaskIdRequest;
import danik.may.dto.response.TaskResponse;
import danik.may.entity.Role;
import danik.may.entity.Task;
import danik.may.entity.User;
import danik.may.repository.TaskRepository;
import danik.may.validator.TaskValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    private TaskRepository repository = mock(TaskRepository.class);
    private UserService userService = mock(UserService.class);

    private TaskService taskService = new TaskService(repository, new TaskValidator(userService));

    @Test
    void getForSimpleUser() {
        //Given
        TaskIdRequest taskId = new TaskIdRequest(1);
        Task task = Task.builder()
                .id(taskId.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("admin")
                .implementer("danik").build();
        User user = User.builder()
                .username("alex")
                .role(Role.ROLE_USER)
                .email("alex@example.com").build();
        //When
        when(repository.findById(taskId.getId())).thenReturn(task);
        when(userService.getCurrentUser()).thenReturn(user);

        //Then
        taskService.get(taskId);
    }

    @Test
    void getForExecutorUser() {
        String expectedAuthor = "user";

        //Given
        TaskIdRequest taskId = new TaskIdRequest(1);
        Task task = Task.builder()
                .id(taskId.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("admin")
                .implementer("danik").build();
        User user = User.builder()
                .username("alex")
                .role(Role.ROLE_USER)
                .email("alex@example.com").build();

        when(repository.findById(taskId.getId())).thenReturn(task);
        when(repository.existsById(taskId.getId())).thenReturn(true);
        when(userService.getCurrentUser()).thenReturn(user);

        //Then
        TaskResponse resultTsk = taskService.get(taskId);

//       assertTrue(resultTsk.isSuccess());
//       assertTrue(resultTsk.getError() == null);
        assertEquals(expectedAuthor, resultTsk.getBody().getAuthor());
    }

    @Test
    void getForAdmin() {
        //Given
        TaskIdRequest taskId = new TaskIdRequest(1);
        Task task = Task.builder()
                .id(taskId.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("admin")
                .implementer("danik").build();
        User user = User.builder()
                .username("alex")
                .role(Role.ROLE_USER)
                .email("alex@example.com").build();

        when(repository.findById(taskId.getId())).thenReturn(task);
        when(userService.getCurrentUser()).thenReturn(user);
        taskService.get(taskId);
    }
}