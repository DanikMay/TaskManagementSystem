package danik.may.service;

import danik.may.dao.TaskDAO;
import danik.may.dto.request.task.CreateTaskRequest;
import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.dto.response.task.*;
import danik.may.entity.Role;
import danik.may.entity.Task;
import danik.may.exception.NoAccessForUpdateTaskException;
import danik.may.exception.TaskIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static danik.may.mapper.TaskMapper.map;
import static danik.may.util.OperationStatusUtil.*;
import static danik.may.validator.TaskValidator.isValidForImplementer;

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
     * @return ответ с данными либо об успехе операции, либо об ошибке
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
     * @return ответ с данными о задаче и успехе операции или об ошибке
     */
    public GetSingleTaskResponse get(TaskIdRequest taskIdRequest) {
        GetSingleTaskResponse singleTaskResponse = new GetSingleTaskResponse();

        try {
            Task task = dao.get(taskIdRequest, isAdmin(), getUsername());
            map(task, singleTaskResponse);
            singleTaskResponse.setOperationStatus(getSuccessOp());
        } catch (TaskIdNotFoundException ex) {
            singleTaskResponse.setOperationStatus(getDataBaseErrorOp(taskIdRequest.getId()));
        } catch (NoAccessForUpdateTaskException ex) {
            singleTaskResponse.setOperationStatus(getAccessErrorOp(getUsername(), taskIdRequest.getId()));
        }

        return singleTaskResponse;
    }

    /**
     * Достаёт список задач, для админа или исполнителя, и формирует ответ
     *
     * @return ответ с данными о задачах и успехе операции или об ошибке
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
     * @return ответ с данными либо об успехе операции, либо об ошибке
     */
    public UpdateTaskResponse update(UpdateTaskRequest updateTaskRequest) {
        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse(getSuccessOp());

        if (isAdmin() || isValidForImplementer(updateTaskRequest)) {
            try {
                dao.update(updateTaskRequest, isAdmin(), getUsername());
            } catch (TaskIdNotFoundException ex) {
                updateTaskResponse.setOperationStatus(getDataBaseErrorOp(updateTaskRequest.getId()));
            } catch (NoAccessForUpdateTaskException ex) {
                updateTaskResponse.setOperationStatus(getAccessErrorOp(getUsername(), updateTaskRequest.getId()));
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
     * @return ответ с данными либо об успехе операции, либо об ошибке
     */
    public DeleteTaskResponse delete(TaskIdRequest taskIdRequest) {
        DeleteTaskResponse deleteTaskResponse = new DeleteTaskResponse(getSuccessOp());

        try {
            dao.delete(taskIdRequest);
            deleteTaskResponse.setOperationStatus(getSuccessOp());
        } catch (TaskIdNotFoundException ex) {
            deleteTaskResponse.setOperationStatus(getDataBaseErrorOp(taskIdRequest.getId()));
        }

        return deleteTaskResponse;
    }

    /**
     * Проверяет, есть ли у пользователя роль админа
     * @return результат проверки
     */
    private boolean isAdmin() {
        return userService.getCurrentUser().getRole().equals(Role.ROLE_ADMIN);
    }

    /**
     * Достаёт имя текущего пользователя
     * @return имя
     */
    private String getUsername() {
        return userService.getCurrentUser().getUsername();
    }
}

