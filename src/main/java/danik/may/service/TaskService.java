package danik.may.service;

import danik.may.entity.Task;
import danik.may.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public void save (Task task) {
        repository.save(task);
    }

    public Task get(Integer taskId) {
        Task resultTask = new Task();
        if(repository.existsById(taskId)) {
            resultTask = repository.findById(taskId).get();
        }
        return resultTask;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public String delete(Integer taskId) {
        String result = String.format("Задача с id: %d не найдена", taskId);
        if(repository.existsById(taskId)) {
            repository.deleteById(taskId);
            result = String.format("Задача с id: %d успешно удалена", taskId);
        }
        return  result;
    }
}

