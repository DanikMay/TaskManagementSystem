package danik.may.service;

import danik.may.dao.TaskDAO;
import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.dto.response.task.GetSingleTaskResponse;
import danik.may.dto.response.task.UpdateTaskResponse;
import danik.may.entity.Role;
import danik.may.entity.Task;
import danik.may.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskDAO dao;
    @Mock
    private UserService userService;
    @InjectMocks
    private TaskService taskService;

    @Test
    void getByNotImplementer() {
        //Given
        String expectedErrorTitle = "Ошибка доступа";
        String expectedErrorText = String.format("У пользователя с именем: %s нет прав на доступ к задаче с id: %d",
                "alex", 1);

        TaskIdRequest taskIdRequest = new TaskIdRequest(1);

        User user = User.builder().username("alex").role(Role.ROLE_USER).email("alex@example.com").build();
        boolean isAdmin = false;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        when(dao.get(taskIdRequest, isAdmin, user.getUsername())).thenThrow(new RuntimeException());

        //Then
        GetSingleTaskResponse resultTask = taskService.get(taskIdRequest);

        assertNull(resultTask.getBody());
        assertFalse(resultTask.getOperationStatus().isSuccess());
        assertEquals(expectedErrorTitle, resultTask.getOperationStatus().getError().getTitle());
        assertEquals(expectedErrorText, resultTask.getOperationStatus().getError().getText());
    }

    @Test
    void getByImplementer() {
        //Given
        int expectedId = 2;
        String expectedHeader = "header1";
        String expectedDescription = "description1";
        String expectedStatus = "ToDo";
        String expectedPriority = "Middle";
        String expectedAuthor = "daniil";
        String expectedImplementer = "kate";

        TaskIdRequest taskIdRequest = new TaskIdRequest(2);
        Task task = Task.builder()
                .id(taskIdRequest.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("daniil")
                .implementer("kate").build();

        User user = User.builder().username("kate").role(Role.ROLE_USER).email("kate@example.com").build();
        boolean isAdmin = false;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        when(dao.get(taskIdRequest, isAdmin, user.getUsername())).thenReturn(task);

        //Then
        GetSingleTaskResponse resultTask = taskService.get(taskIdRequest);

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
        int expectedId = 3;
        String expectedHeader = "header1";
        String expectedDescription = "description1";
        String expectedStatus = "ToDo";
        String expectedPriority = "Middle";
        String expectedAuthor = "admin";
        String expectedImplementer = "daniil";

        TaskIdRequest taskIdRequest = new TaskIdRequest(3);

        Task task = Task.builder()
                .id(taskIdRequest.getId())
                .header("header1")
                .description("description1")
                .status("ToDo")
                .priority("Middle")
                .author("admin")
                .implementer("daniil").build();

        User user = User.builder().username("admin").role(Role.ROLE_ADMIN).email("admin@example.com").build();
        boolean isAdmin = true;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        when(dao.get(taskIdRequest, isAdmin, user.getUsername())).thenReturn(task);

        //Then
        GetSingleTaskResponse resultTask = taskService.get(taskIdRequest);

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
    void getByWrongId() {
        //Given
        String expectedErrorTitle = "Ошибка базы данных";
        String expectedErrorText = String.format("Задача с id: %d не найдена", 4);

        TaskIdRequest taskIdRequest = new TaskIdRequest(4);

        User user = User.builder().username("john").role(Role.ROLE_USER).email("john@example.com").build();
        boolean isAdmin = true;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        when(dao.get(taskIdRequest, isAdmin, user.getUsername())).thenThrow(new RuntimeException());

        //Then
        GetSingleTaskResponse resultTask = taskService.get(taskIdRequest);

        assertNull(resultTask.getBody());
        assertFalse(resultTask.getOperationStatus().isSuccess());
        assertEquals(expectedErrorTitle, resultTask.getOperationStatus().getError().getTitle());
        assertEquals(expectedErrorText, resultTask.getOperationStatus().getError().getText());
    }

    @Test
    void updateByNotImplementer() {
        //Given
        String expectedErrorTitle = "Ошибка доступа";
        String expectedErrorText = String.format("У пользователя с именем: %s нет прав на доступ к задаче с id: %d",
                "alex", 1);

        UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder().id(1).status("Done").build();

        User user = User.builder().username("alex").role(Role.ROLE_USER).email("alex@example.com").build();
        boolean isAdmin = false;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        doThrow(new RuntimeException()).when(dao).update(updateTaskRequest, isAdmin, user.getUsername());

        //Then
        UpdateTaskResponse updateTaskResponse = taskService.update(updateTaskRequest);

        assertFalse(updateTaskResponse.getOperationStatus().isSuccess());
        assertEquals(expectedErrorTitle, updateTaskResponse.getOperationStatus().getError().getTitle());
        assertEquals(expectedErrorText, updateTaskResponse.getOperationStatus().getError().getText());
    }

    @Test
    void updateByImplementer() {
        //Given
        UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder().id(1).status("Done").build();

        User user = User.builder().username("alex").role(Role.ROLE_USER).email("alex@example.com").build();
        boolean isAdmin = false;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        doNothing().when(dao).update(updateTaskRequest, isAdmin, user.getUsername());

        //Then
        UpdateTaskResponse updateTaskResponse = taskService.update(updateTaskRequest);

        assertTrue(updateTaskResponse.getOperationStatus().isSuccess());
        assertNull(updateTaskResponse.getOperationStatus().getError());
    }

    @Test
    void updateByAdmin() {
        //Given
        UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
                .id(2)
                .header("header2")
                .description("description2")
                .status("Done")
                .priority("High")
                .build();

        User user = User.builder().username("alex").role(Role.ROLE_USER).email("alex@example.com").build();
        boolean isAdmin = false;

        //When
        when(userService.getCurrentUser()).thenReturn(user);
        doThrow(new RuntimeException()).when(dao).update(updateTaskRequest, isAdmin, user.getUsername());

        //Then
        UpdateTaskResponse updateTaskResponse = taskService.update(updateTaskRequest);

        assertTrue(updateTaskResponse.getOperationStatus().isSuccess());
        assertNull(updateTaskResponse.getOperationStatus().getError());
    }

    @Test
    void updateByWrongId() {
        //Given

        //When

        //Then

    }
}