package danik.may.controller;

import danik.may.dto.request.task.CreateTaskRequest;
import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.dto.response.task.*;
import danik.may.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Работа с задачами")
public class TaskController {
    private final TaskService service;

    @PostMapping("/create")
    @Operation(summary = "Создание задачи на основе JSON")
    @PreAuthorize("hasRole('ADMIN')")
    public CreateTaskResponse create(@RequestBody CreateTaskRequest createTaskRequest) {
        return service.create(createTaskRequest);
    }

    @PostMapping("/get")
    @Operation(summary = "Просмотр задач c заданным id")
    public GetSingleTaskResponse get(@RequestBody TaskIdRequest taskIdRequest) {
        return service.get(taskIdRequest);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Просмотр всех задач")
    @PreAuthorize("hasRole('ADMIN')")
    public GetAllTaskResponse getAll() {
        return service.getAll();
    }


    @PostMapping("/update")
    @Operation(summary = "Обновление задачи на основе JSON")
    public UpdateTaskResponse update(@RequestBody UpdateTaskRequest taskRequest) {
        return service.update(taskRequest);
    }

    @PostMapping("/delete")
    @Operation(summary = "Удаление задачи с заданным id")
    @PreAuthorize("hasRole('ADMIN')")
    public DeleteTaskResponse delete(@RequestBody TaskIdRequest taskIdRequest) {
        return service.delete(taskIdRequest);
    }
}
