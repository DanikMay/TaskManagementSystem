package danik.may.dao;

import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.entity.Task;
import danik.may.exception.NoAccessForUpdateTaskException;
import danik.may.exception.TaskIdNotFoundException;
import danik.may.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDAO {
    private final TaskRepository repo;

    /**
     * Создаёт задачу
     */
    public void create(Task task) {
        repo.save(task);
    }

    /**
     * Достаёт задачу по id для Админа или Юзера
     * Если задача с таким id не найдена возбуждается исключение
     * Если юзер не админ, делается проверка на исполнителя, если проверка провалена, будет выброшено исключение
     */
    @Transactional
    public Task get(TaskIdRequest taskIdRequest, boolean isAdmin, String userName) throws NoAccessForUpdateTaskException, TaskIdNotFoundException {
        int id = taskIdRequest.getId();

        if (repo.existsById(id)) {
            if (isAdmin || repo.isImplementer(id, userName)) {
                return repo.findById(id);
            } else {
                throw new NoAccessForUpdateTaskException("У пользователя нет прав для доступа к этой задаче");
            }
        } else {
            throw new TaskIdNotFoundException("Задачи с таким id не существует");
        }
    }

    /**
     * Возвращает список всех задач для админа или исполнителя
     */
    public List<Task> getAll(boolean isAdmin, String userName) {
        List<Task> taskList = null;

        if (isAdmin) {
            taskList = repo.findAll();
        } else {
            taskList = repo.findAllByImplementer(userName);
        }
        return taskList;
    }

    /**
     * Обновляет задачу по id для Админа или Юзера
     * Если задача с таким id не найдена возбуждается исключение
     * Если юзер не админ, делается проверка на исполнителя, если проверка провалена, будет выброшено исключение
     */
    @Transactional
    public void update(UpdateTaskRequest updateTaskRequest, boolean isAdmin, String userName) throws NoAccessForUpdateTaskException, TaskIdNotFoundException {
        if (repo.existsById(updateTaskRequest.getId())) {
            if (isAdmin || repo.isImplementer(updateTaskRequest.getId(), userName)) {
                repo.update(updateTaskRequest.getId(), updateTaskRequest.getHeader(), updateTaskRequest.getDescription(),
                        updateTaskRequest.getPriority(), updateTaskRequest.getStatus(),
                        updateTaskRequest.getAuthor(), updateTaskRequest.getImplementer());
            } else {
                throw new NoAccessForUpdateTaskException("У пользователя нет прав для доступа к этой задаче");
            }
        } else {
            throw new TaskIdNotFoundException("Задачи с таким id не существует");
        }
    }

    /**
     * Удаляет задачу по id, если задача с таким id существует, иначе выбрасывает исключение
     */
    @Transactional
    public void delete(TaskIdRequest taskIdRequest) throws TaskIdNotFoundException {
        int id = taskIdRequest.getId();

        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new TaskIdNotFoundException("Задачи с таким id не существует");
        }
    }
}
