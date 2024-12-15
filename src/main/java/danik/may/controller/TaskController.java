package danik.may.controller;

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
    public Task get(@RequestBody Integer taskId) {
        return service.get(taskId);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    //показывает задачу c заданным id
    public List<Task> getAll() {
        return service.getAll();
    }


    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    //обновляет задачу на основе JSON
    public void update(@RequestBody Task task) {

    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    //удаляет задачу с заданным id
    public String delete(@RequestBody Integer taskId) {
        return service.delete(taskId);
    }
}
