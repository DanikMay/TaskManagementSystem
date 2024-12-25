package danik.may.mapper;

import danik.may.dto.request.TaskRequest;
import danik.may.dto.response.TaskBody;
import danik.may.dto.response.TaskResponse;
import danik.may.entity.Task;

public class TaskMapper {
    public static void map(Task currentTask, TaskRequest updateTask) {
        if (updateTask.getHeader() != null) {
            currentTask.setHeader(updateTask.getHeader());
        }
        if (updateTask.getDescription() != null) {
            currentTask.setDescription(updateTask.getDescription());
        }
        if (updateTask.getStatus() != null) {
            currentTask.setStatus(updateTask.getStatus());
        }
        if (updateTask.getPriority() != null) {
            currentTask.setPriority(updateTask.getPriority());
        }
        if (updateTask.getAuthor() != null) {
            currentTask.setAuthor(updateTask.getAuthor());
        }
        if (updateTask.getImplementer() != null) {
            currentTask.setImplementer(updateTask.getImplementer());
        }
    }

    public static void map(Task task, TaskResponse response) {
        response.setBody(new TaskBody());
        response.getBody().setId(task.getId());
        response.getBody().setHeader(task.getHeader());
        response.getBody().setDescription(task.getDescription());
        response.getBody().setStatus(task.getStatus());
        response.getBody().setPriority(task.getPriority());
        response.getBody().setAuthor(task.getAuthor());
        response.getBody().setImplementer(task.getImplementer());
    }
}
