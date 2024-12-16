package danik.may.service;

import danik.may.dto.UpdateTaskRequest;
import danik.may.entity.Task;
import danik.may.mapper.TaskMapper;
import danik.may.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public void save(Task task) {
        repository.save(task);
    }

    public Task get(int id) {
        Task resultTask = new Task();
        if (repository.existsById(id)) {
            resultTask = repository.findById(id);
        }
        return resultTask;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public String delete(int id) {
        String result = String.format("Задача с id: %d не найдена", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            result = String.format("Задача с id: %d успешно удалена", id);
        }
        return result;
    }

    public String update(UpdateTaskRequest updateTaskRequest) {
        String result = String.format("Задача с id: %d не найдена", updateTaskRequest.getId());
        if (repository.existsById(updateTaskRequest.getId())) {
            Task task = repository.findById(updateTaskRequest.getId());
            TaskMapper.UpdateTask(task, updateTaskRequest);
            repository.save(task);
            result = String.format("Задача с id: %d успешно обновлена", updateTaskRequest.getId());
        }
        return result;
    }
}

