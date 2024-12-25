package danik.may.service;

import danik.may.dto.request.TaskIdRequest;
import danik.may.dto.request.TaskRequest;
import danik.may.dto.response.Error;
import danik.may.dto.response.OperationStatusResponse;
import danik.may.dto.response.TaskResponse;
import danik.may.entity.Task;
import danik.may.repository.TaskRepository;
import danik.may.validator.TaskValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static danik.may.mapper.TaskMapper.map;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskValidator validator;

    public OperationStatusResponse save(TaskRequest taskRequest) {
        OperationStatusResponse operationStatus = new OperationStatusResponse();
        Task task = new Task();
        map(task, taskRequest);
        repository.save(task);
        operationStatus.setSuccess(true);
        return operationStatus;
    }

    public TaskResponse get(TaskIdRequest taskIdRequest) {
        int id = taskIdRequest.getId();
        TaskResponse taskResponse = new TaskResponse();
        if (repository.existsById(id)) {
            map(repository.findById(id), taskResponse);
            taskResponse.setSuccess(true);
        } else {
            taskResponse.setSuccess(false);
            Error error = new Error();
            error.setTitle("Ошибка базы данных");
            error.setText(String.format("Задача с id: %d не найдена", id));
            taskResponse.setError(new Error());
        }
        return taskResponse;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public OperationStatusResponse delete(TaskIdRequest taskIdRequest) {
        int id = taskIdRequest.getId();
        OperationStatusResponse operationStatus = new OperationStatusResponse();

        if (repository.existsById(id)) {
            repository.deleteById(id);
            operationStatus.setSuccess(true);
        } else {
            operationStatus.setSuccess(false);
            Error error = new Error();
            error.setTitle("Ошибка базы данных");
            error.setText(String.format("Задача с id: %d не найдена", id));
            operationStatus.setError(new Error());
        }
        return operationStatus;
    }

    public OperationStatusResponse update(TaskRequest taskRequest) {
        int id = taskRequest.getId();
        OperationStatusResponse operationStatus = validator.getPermissionForUpdate(taskRequest);
        if (operationStatus.isSuccess()) {
            if (repository.existsById(id)) {
                Task task = repository.findById(id);
                map(task, taskRequest);
                repository.save(task);
            } else {
                operationStatus.setSuccess(false);
                Error error = new Error();
                error.setTitle("Ошибка базы данных");
                error.setText(String.format("Задача с id: %d не найдена", id));
                operationStatus.setError(new Error());
            }
        }
        return operationStatus;
    }
}

