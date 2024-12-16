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
    @Operation(summary = "Создание задачи")
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    //создает задачу на основе JSON
    public void create(@RequestBody Task task) {
        service.save(task);
    }

    @PostMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    //показывает задачу c заданным id
    public Task get(@RequestBody TaskIdRequest taskIdRequest) {
        return service.get(taskIdRequest.getId());
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    //показывает все задачи
    public List<Task> getAll() {
        return service.getAll();
    }


    @PatchMapping("/update")
    //обновляет задачу на основе JSON
    public String update(@RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        String message = validator.getPermission(updateTaskRequest);
        if(message.equals(validator.SUCCESS)) {
            message += service.update(updateTaskRequest);
        }
        return message;
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    //удаляет задачу с заданным id
    public String delete(@RequestBody TaskIdRequest taskIdRequest) {
        return service.delete(taskIdRequest.getId());
    }
}
