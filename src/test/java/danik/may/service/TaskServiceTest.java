package danik.may.service;

import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.response.op_status.Error;
import danik.may.dto.response.task.TaskResponse;
import danik.may.entity.Role;
import danik.may.entity.Task;
import danik.may.entity.User;
import danik.may.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    private final TaskRepository repository = mock(TaskRepository.class);
    private final UserService userService = mock(UserService.class);
    private final TaskService taskService = new TaskService(repository, userService);

    @Test
    void getBySimpleUser() {
        //Given
        String expectedErrorTitle = "Ошибка доступа";
        String expectedErrorText = String.format("У пользователя с именем: %s нет права на доступ к задаче с id: %d",
                "alex", 1);

        TaskIdRequest taskId = new TaskIdRequest(1);
        Task task = Task.builder()
                .id(taskId.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("user")
                .implementer("danik").build();

        User user = User.builder().username("alex").role(Role.ROLE_USER).email("alex@example.com").build();

        //When
        when(repository.findById(taskId.getId())).thenReturn(task);
        when(repository.existsById(taskId.getId())).thenReturn(true);
        when(userService.getCurrentUser()).thenReturn(user);

        //Then
        TaskResponse resultTask = taskService.get(taskId);

        assertNull(resultTask.getBody());
        assertFalse(resultTask.getOperationStatus().isSuccess());
        assertEquals(resultTask.getOperationStatus().getError().getTitle(), expectedErrorTitle);
        assertEquals(resultTask.getOperationStatus().getError().getText(), expectedErrorText);
    }

    @Test
    void getByImplementerUser() {
        //Given
        int expectedId = 1;
        String expectedHeader = "header1";
        String expectedDescription = "description1";
        String expectedStatus = "ToDo";
        String expectedPriority = "Middle";
        String expectedAuthor = "user";
        String expectedImplementer = "danik";

        TaskIdRequest taskId = new TaskIdRequest(1);
        Task task = Task.builder()
                .id(taskId.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("user")
                .implementer("danik").build();

        User user = User.builder().username("alex").role(Role.ROLE_USER).email("alex@example.com").build();

        //When
        when(repository.findById(taskId.getId())).thenReturn(task);
        when(repository.existsById(taskId.getId())).thenReturn(true);
        when(userService.getCurrentUser()).thenReturn(user);

        //Then
        TaskResponse resultTask = taskService.get(taskId);

        assertTrue(resultTask.getOperationStatus().isSuccess());
        assertNull(resultTask.getOperationStatus().getError());
        assertEquals(expectedId, resultTask.getBody().getId());
        assertEquals(expectedHeader, resultTask.getBody().getHeader());
        assertEquals(expectedDescription, resultTask.getBody().getDescription());
        assertEquals(expectedStatus, resultTask.getBody().getStatus());
        assertEquals(expectedPriority, resultTask.getBody().getPriority());
        assertEquals(expectedAuthor, resultTask.getBody().getAuthor());
        assertEquals(expectedImplementer, resultTask.getBody().getImplementer());
    }

    @Test
    void getByAdmin() {
        //Given
        int expectedId = 1;
        String expectedHeader = "header1";
        String expectedDescription = "description1";
        String expectedStatus = "ToDo";
        String expectedPriority = "Middle";
        String expectedAuthor = "user";
        String expectedImplementer = "danik";

        TaskIdRequest taskId = new TaskIdRequest(1);

        Task task = Task.builder()
                .id(taskId.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("user")
                .implementer("danik").build();

        User user = User.builder().username("admin").role(Role.ROLE_ADMIN).email("admin@example.com").build();

        //When
        when(repository.findById(taskId.getId())).thenReturn(task);
        when(repository.existsById(taskId.getId())).thenReturn(true);
        when(userService.getCurrentUser()).thenReturn(user);

        //Then
        TaskResponse resultTask = taskService.get(taskId);

        assertTrue(resultTask.getOperationStatus().isSuccess());
        assertNull(resultTask.getOperationStatus().getError());
        assertEquals(expectedId, resultTask.getBody().getId());
        assertEquals(expectedHeader, resultTask.getBody().getHeader());
        assertEquals(expectedDescription, resultTask.getBody().getDescription());
        assertEquals(expectedStatus, resultTask.getBody().getStatus());
        assertEquals(expectedPriority, resultTask.getBody().getPriority());
        assertEquals(expectedAuthor, resultTask.getBody().getAuthor());
        assertEquals(expectedImplementer, resultTask.getBody().getImplementer());
    }
}