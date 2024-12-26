package danik.may.controller;

import danik.may.dto.request.task.TaskCreateRequest;
import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.TaskUpdateRequest;
import danik.may.dto.response.op_status.OperationStatus;
import danik.may.dto.response.task.AllTaskResponse;
import danik.may.dto.response.task.TaskResponse;
import danik.may.entity.Task;
import danik.may.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping("/create")
    @Operation(summary = "Создание задачи на основе JSON")
    @PreAuthorize("hasRole('ADMIN')")
    public OperationStatus create(@RequestBody TaskCreateRequest taskCreateRequest) {
        return service.create(taskCreateRequest);
    }

    @PostMapping("/get")
    @Operation(summary = "Просмотр задач c заданным id")
    public TaskResponse get(@RequestBody TaskIdRequest taskIdRequest) {
        return service.get(taskIdRequest);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Просмотр всех задач")
    @PreAuthorize("hasRole('ADMIN')")
    public AllTaskResponse getAll() {
        return service.getAll();
    }


    @PostMapping("/update")
    @Operation(summary = "Обновление задачи на основе JSON")
    public OperationStatus update(@RequestBody TaskUpdateRequest taskRequest) {
        return service.update(taskRequest);
    }

    @PostMapping("/delete")
    @Operation(summary = "Удаление задачи с заданным id")
    @PreAuthorize("hasRole('ADMIN')")
    public OperationStatus delete(@RequestBody TaskIdRequest taskIdRequest) {
        return service.delete(taskIdRequest);
    }
}
