package danik.may.controller;

import danik.may.dto.TaskIdRequest;
import danik.may.dto.UpdateTaskRequest;
import danik.may.entity.Task;
import danik.may.service.TaskService;
import danik.may.validator.TaskByRoleValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Работа с задачами")
public class TaskController {
    private final TaskService service;
    private final TaskByRoleValidator validator;

    @PostMapping("/create")
    @Operation(summary = "Создание задачи на основе JSON")
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@RequestBody Task task) {
        service.save(task);
    }

    @PostMapping("/get")
    @Operation(summary = "Просмотр задач c заданным id")
    @PreAuthorize("hasRole('ADMIN')")
    public Task get(@RequestBody TaskIdRequest taskIdRequest) {
        return service.get(taskIdRequest.getId());
    }

    @GetMapping("/get-all")
    @Operation(summary = "Просмотр всех задач")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> getAll() {
        return service.getAll();
    }


    @PostMapping("/update")
    @Operation(summary = "Обновление задачи на основе JSON")
    public String update(@RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        String message = validator.getPermission(updateTaskRequest);
        if (message.equals(validator.SUCCESS)) {
            message += service.update(updateTaskRequest);
        }
        return message;
    }

    @PostMapping("/delete")
    @Operation(summary = "Удаление задачи с заданным id")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestBody TaskIdRequest taskIdRequest) {
        return service.delete(taskIdRequest.getId());
    }
}
