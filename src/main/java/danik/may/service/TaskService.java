package danik.may.service;

import danik.may.dao.TaskDAO;
import danik.may.dto.request.task.CreateTaskRequest;
import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.dto.response.task.*;
import danik.may.entity.Role;
import danik.may.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static danik.may.mapper.TaskMapper.map;
import static danik.may.util.OperationStatusUtil.*;
import static danik.may.validator.TaskValidator.isValid;

/**
 * Обрабатывает запросы и формирует ответы для CRUD-операций над задачами
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDAO dao;
    private final UserService userService;

    /**
     * Создаёт задачу, если юзер - админ, и формирует ответ
     *
     * @param createTaskRequest данные для создания
     * @return ответ с данными об ошибке и статусе операции
     */
    public CreateTaskResponse create(CreateTaskRequest createTaskRequest) {
        Task task = new Task();

        map(task, createTaskRequest);
        dao.create(task);

        return new CreateTaskResponse(getSuccessOp());
    }

    /**
     * Достаёт задачу по id, если юзер - админ или исполнитель, и формирует ответ
     *
     * @param taskIdRequest id
     * @return ответ с данными о задаче, и об ошибке и статусе операции
     */
    public GetSingleTaskResponse get(TaskIdRequest taskIdRequest) {
        GetSingleTaskResponse singleTaskResponse = new GetSingleTaskResponse();

        try {
            Task task = dao.get(taskIdRequest, isAdmin(), getUsername());
            map(task, singleTaskResponse);
            singleTaskResponse.setOperationStatus(getSuccessOp());
        } catch (RuntimeException ex) {
            singleTaskResponse.setOperationStatus(getDataBaseErrorOp(taskIdRequest.getId()));
        }

        return singleTaskResponse;
    }

    /**
     * Достаёт список задач, для админа или исполнителя, и формирует ответ
     *
     * @return ответ с данными о задачах, и об ошибке и статусе операции
     */
    public GetAllTaskResponse getAll() {
        GetAllTaskResponse allTaskResponse = new GetAllTaskResponse();

        List<Task> taskList = dao.getAll(isAdmin(), getUsername());
        map(taskList, allTaskResponse);
        allTaskResponse.setOperationStatus(getSuccessOp());

        return allTaskResponse;
    }

    /**
     * Обновляет задачу, если юзер - админ или исполнитель, и формирует ответ
     *
     * @param updateTaskRequest данные для обновления задачи
     * @return ответ с данными об ошибке и статусе операции
     */
    public UpdateTaskResponse update(UpdateTaskRequest updateTaskRequest) {
        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse(getSuccessOp());

        if (isValid(updateTaskRequest, isAdmin())) {
            try {
                Task task = new Task();
                map(task, updateTaskRequest);
                dao.update(task, isAdmin(), getUsername());
            } catch (RuntimeException ex) {
                updateTaskResponse.setOperationStatus(getDataBaseErrorOp(updateTaskRequest.getId()));
            }
        } else {
            updateTaskResponse.setOperationStatus(getInvalidTaskRequestErrorOp(getUsername()));
        }

        return updateTaskResponse;
    }

    /**
     * Удаляет задачу по id, если юзер - админ, и формирует ответ
     *
     * @param taskIdRequest id
     * @return ответ с данными об ошибке и статусе операции
     */
    public DeleteTaskResponse delete(TaskIdRequest taskIdRequest) {
        DeleteTaskResponse deleteTaskResponse = new DeleteTaskResponse(getSuccessOp());

        try {
            dao.delete(taskIdRequest);
            deleteTaskResponse.setOperationStatus(getSuccessOp());
        } catch (RuntimeException ex) {
            deleteTaskResponse.setOperationStatus(getDataBaseErrorOp(taskIdRequest.getId()));
        }

        return deleteTaskResponse;
    }

    public boolean isAdmin() {
        return userService.getCurrentUser().getRole().equals(Role.ROLE_ADMIN);
    }

    public String getUsername() {
        return userService.getCurrentUser().getUsername();
    }
}

