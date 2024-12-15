package danik.may.service;

import danik.may.entity.Task;
import danik.may.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    public Task save (Task task) {
        return repository.save(task);
    }
}
