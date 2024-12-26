package danik.may.mapper;

import danik.may.dto.request.task.TaskCreateRequest;
import danik.may.dto.request.task.TaskUpdateRequest;
import danik.may.dto.response.task.TaskBody;
import danik.may.entity.Task;

import java.util.List;

/**
 * Преобразует данные связанные с задачами
 */
public class TaskMapper {
    /**
     * Обновляет поля задачи данными из запроса, если данные не null
     *
     * @param task              текущие данные
     * @param taskUpdateRequest данные для обновления
     */
    public static void map(Task task, TaskUpdateRequest taskUpdateRequest) {
        if (taskUpdateRequest.getHeader() != null) {
            task.setHeader(taskUpdateRequest.getHeader());
        }
        if (taskUpdateRequest.getDescription() != null) {
            task.setDescription(taskUpdateRequest.getDescription());
        }
        if (taskUpdateRequest.getStatus() != null) {
            task.setStatus(taskUpdateRequest.getStatus());
        }
        if (taskUpdateRequest.getPriority() != null) {
            task.setPriority(taskUpdateRequest.getPriority());
        }
        if (taskUpdateRequest.getAuthor() != null) {
            task.setAuthor(taskUpdateRequest.getAuthor());
        }
        if (taskUpdateRequest.getImplementer() != null) {
            task.setImplementer(taskUpdateRequest.getImplementer());
        }
    }

    /**
     * Заполняет поля ответа данными из задачи
     *
     * @param task     данные задачи
     * @param taskBody объект для отправки ответа
     */
    public static void map(Task task, TaskBody taskBody) {
        taskBody.setId(task.getId());
        taskBody.setHeader(task.getHeader());
        taskBody.setDescription(task.getDescription());
        taskBody.setStatus(task.getStatus());
        taskBody.setPriority(task.getPriority());
        taskBody.setAuthor(task.getAuthor());
        taskBody.setImplementer(task.getImplementer());
    }

    /**
     * Заполняет поля задачи данными из запроса
     *
     * @param task              сущность для создания задачи
     * @param taskCreateRequest данные для создания
     */
    public static void map(Task task, TaskCreateRequest taskCreateRequest) {
        task.setHeader(taskCreateRequest.getHeader());
        task.setHeader(taskCreateRequest.getDescription());
        task.setHeader(taskCreateRequest.getStatus());
        task.setHeader(taskCreateRequest.getPriority());
        task.setHeader(taskCreateRequest.getAuthor());
        task.setHeader(taskCreateRequest.getImplementer());
    }

    /**
     * Заполняет ответ, задачами из списка
     *
     * @param taskList задачи
     * @param body     объект для отправки ответа
     */
    public static void map(List<Task> taskList, List<TaskBody> body) {
        for (Task task : taskList) {
            TaskBody taskBody = new TaskBody();
            map(task, new TaskBody());
            body.add(taskBody);
        }
    }
}
