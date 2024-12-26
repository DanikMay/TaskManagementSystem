package danik.may.service;

import danik.may.dto.request.task.TaskCreateRequest;
import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.TaskUpdateRequest;
import danik.may.dto.response.op_status.OperationStatus;
import danik.may.dto.response.task.AllTaskResponse;
import danik.may.dto.response.task.TaskBody;
import danik.may.dto.response.task.TaskResponse;
import danik.may.entity.Task;
import danik.may.mapper.TaskMapper;
import danik.may.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static danik.may.mapper.TaskMapper.map;
import static danik.may.provider.OperationStatusProvider.*;
import static danik.may.validator.TaskValidator.getPermissionToUpdate;

/**
 * Обрабатывает запросы и формирует ответы для CRUD-операций над задачами
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repo;
    private final UserService userService;

    /**
     * Создаёт задачу, если юзер - админ, и формирует ответ
     *
     * @param taskCreateRequest данные для создания
     * @return ответ с данными об ошибке и статусе операции
     */
    public OperationStatus create(TaskCreateRequest taskCreateRequest) {
        Task task = new Task();
        map(task, taskCreateRequest);
        repo.save(task);

        return getSuccessOp();
    }

    /**
     * Достаёт задачу по id, если юзер - админ или исполнитель, и формирует ответ
     *
     * @param taskIdRequest id
     * @return ответ с данными о задаче, и об ошибке и статусе операции
     */
    public TaskResponse get(TaskIdRequest taskIdRequest) {
        int id = taskIdRequest.getId();
        TaskResponse taskResponse = new TaskResponse(new TaskBody());

        if (repo.existsById(id)) {
            map(repo.findById(id), taskResponse.getBody());
            taskResponse.setOperationStatus(getSuccessOp());
        } else {
            taskResponse.setOperationStatus(getDataBaseErrorOp(id));
        }

        return taskResponse;
    }

    /**
     * Достаёт список задач, для админа или исполнителя, и формирует ответ
     *
     * @return ответ с данными о задачах, и об ошибке и статусе операции
     */
    public AllTaskResponse getAll() {
        AllTaskResponse allTaskResponse = new AllTaskResponse(new ArrayList<>());
        map(repo.findAll(), allTaskResponse.getBody());
        return allTaskResponse;
    }

    /**
     * Обновляет задачу, если юзер - админ или исполнитель, и формирует ответ
     *
     * @param taskRequest данные для обновления задачи
     * @return ответ с данными об ошибке и статусе операции
     */
    public OperationStatus update(TaskUpdateRequest taskRequest) {
        int id = taskRequest.getId();
        OperationStatus operationStatus = getSuccessOp();

        if (getPermissionToUpdate(taskRequest, userService.getCurrentUser().getRole())) {
            if (repo.existsById(id)) {
                Task task = repo.findById(id);
                TaskMapper.map(task, taskRequest);
                repo.save(task);
            } else {
                operationStatus = getDataBaseErrorOp(id);
            }
        } else {
            operationStatus = getInvalidTaskRequestErrorOp(userService.getCurrentUser().getUsername());
        }

        return operationStatus;
    }

    /**
     * Удаляет задачу по id, если юзер - админ, и формирует ответ
     *
     * @param taskIdRequest id
     * @return ответ с данными об ошибке и статусе операции
     */
    public OperationStatus delete(TaskIdRequest taskIdRequest) {
        int id = taskIdRequest.getId();
        OperationStatus operationStatus = getSuccessOp();

        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            operationStatus = getDataBaseErrorOp(id);
        }

        return operationStatus;
    }
}

