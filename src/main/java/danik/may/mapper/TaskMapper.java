package danik.may.mapper;

import danik.may.dto.UpdateTaskRequest;
import danik.may.entity.Task;

public class TaskMapper {
    public static void UpdateTask(Task currentTask, UpdateTaskRequest updateTask){
        if(updateTask.getHeader() != null) {
            currentTask.setHeader(updateTask.getHeader());
        }
        if(updateTask.getDescription() != null) {
            currentTask.setDescription(updateTask.getDescription());
        }
        if(updateTask.getStatus() != null) {
            currentTask.setStatus(updateTask.getStatus());
        }
        if(updateTask.getPriority() != null) {
            currentTask.setPriority(updateTask.getPriority());
        }
        if(updateTask.getAuthor() != null) {
            currentTask.setAuthor(updateTask.getAuthor());
        }
        if(updateTask.getExecutor() != null) {
            currentTask.setExecutor(updateTask.getExecutor());
        }
    }
}
