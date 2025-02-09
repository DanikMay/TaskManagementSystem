package danik.may.mapper;

import danik.may.dto.request.task.CreateTaskRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.dto.response.task.GetAllTaskResponse;
import danik.may.dto.response.task.GetSingleTaskResponse;
import danik.may.dto.response.task.TaskBody;
import danik.may.entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Преобразует данные связанные с задачами
 */
public class TaskMapper {
    /**
     * Обновляет поля задачи данными из запроса, если данные не null
     *
     * @param task              сущность для обновления задачи
     * @param updateTaskRequest данные для обновления
     */
    public static void map(Task task, UpdateTaskRequest updateTaskRequest) {
        if (updateTaskRequest.getHeader() != null) {
            task.setHeader(updateTaskRequest.getHeader());
        }
        if (updateTaskRequest.getDescription() != null) {
            task.setDescription(updateTaskRequest.getDescription());
        }
        if (updateTaskRequest.getStatus() != null) {
            task.setStatus(updateTaskRequest.getStatus());
        }
        if (updateTaskRequest.getPriority() != null) {
            task.setPriority(updateTaskRequest.getPriority());
        }
        if (updateTaskRequest.getAuthor() != null) {
            task.setAuthor(updateTaskRequest.getAuthor());
        }
        if (updateTaskRequest.getImplementer() != null) {
            task.setImplementer(updateTaskRequest.getImplementer());
        }
    }

    /**
     * Заполняет поля ответа данными из задачи
     *
     * @param task                  данные задачи
     * @param getSingleTaskResponse объект для отправки ответа
     */
    public static void map(Task task, GetSingleTaskResponse getSingleTaskResponse) {
        TaskBody body = new TaskBody();

        body.setId(task.getId());
        body.setHeader(task.getHeader());
        body.setDescription(task.getDescription());
        body.setStatus(task.getStatus());
        body.setPriority(task.getPriority());
        body.setAuthor(task.getAuthor());
        body.setImplementer(task.getImplementer());

        getSingleTaskResponse.setBody(body);
    }

    /**
     * Заполняет поля задачи данными из запроса
     *
     * @param task              сущность для создания задачи
     * @param createTaskRequest данные для создания
     */
    public static void map(Task task, CreateTaskRequest createTaskRequest) {
        task.setHeader(createTaskRequest.getHeader());
        task.setHeader(createTaskRequest.getDescription());
        task.setHeader(createTaskRequest.getStatus());
        task.setHeader(createTaskRequest.getPriority());
        task.setHeader(createTaskRequest.getAuthor());
        task.setHeader(createTaskRequest.getImplementer());
    }

    /**
     * Заполняет ответ, задачами из списка
     *
     * @param taskList        задачи
     * @param allTaskResponse объект для отправки ответа
     */
    public static void map(List<Task> taskList, GetAllTaskResponse allTaskResponse) {
        List<TaskBody> body = new ArrayList<>();

        for (Task task : taskList) {
            TaskBody taskBody = new TaskBody();

            taskBody.setId(task.getId());
            taskBody.setHeader(task.getHeader());
            taskBody.setDescription(task.getDescription());
            taskBody.setStatus(task.getStatus());
            taskBody.setPriority(task.getPriority());
            taskBody.setAuthor(task.getAuthor());
            taskBody.setImplementer(task.getImplementer());

            body.add(taskBody);
        }

        allTaskResponse.setBody(body);
    }
}
