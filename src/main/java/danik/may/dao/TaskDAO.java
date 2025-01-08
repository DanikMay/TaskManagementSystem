package danik.may.dao;

import danik.may.dto.request.task.TaskIdRequest;
import danik.may.dto.request.task.UpdateTaskRequest;
import danik.may.entity.Task;
import danik.may.mapper.TaskMapper;
import danik.may.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDAO {
    private final TaskRepository repo;

    //Создаёт задачу
    public void create(Task task) {
        repo.save(task);
    }

    /*
    Достаёт задачу по id для Админа или Юзера
    Если задача с таким id не найдена возбуждается исключение
    Если это юзер, делается проверка на исполнителя, если проверка провалена, возбуждается исключение
    */
    public Task get(TaskIdRequest taskIdRequest, boolean isAdmin, String userName) throws RuntimeException {
        int id = taskIdRequest.getId();

        if (repo.existsById(id)) {
            if (isAdmin || repo.isImplementer(id, userName)) {
                return repo.findById(id);
            } else {
                throw new RuntimeException("У пользователя нет прав для доступа к этой задаче");
            }
        } else {
            throw new RuntimeException("Задачи с таким id не существует");
        }
    }

    //Возвращает список всех задач для админа или исполнителя
    public List<Task> getAll(boolean isAdmin, String userName) {
        List<Task> taskList = null;

        if (isAdmin) {
            taskList = repo.findAll();
        } else {
            taskList = repo.findAllByImplementer(userName);
        }
        return taskList;
    }

    //Обновляет задачу, если задача с таким id существует, иначе возбуждает исключение
    public void update(UpdateTaskRequest request, boolean isAdmin, String userName) throws RuntimeException {
        int id = request.getId();

        if (repo.existsById(request.getId())) {
            if (isAdmin || repo.isImplementer(id, userName)) {
                Task task = repo.findById(id);
                TaskMapper.map(task, request);
                repo.save(task);
            } else {
                throw new RuntimeException("У пользователя нет прав для доступа к этой задаче");
            }
        } else {
            throw new RuntimeException("Задачи с таким id не существует");
        }
    }

    //Удаляет задачу по id, если задача с таким id существует, иначе выбрасывает исключение
    public void delete(TaskIdRequest taskIdRequest) throws RuntimeException {
        int id = taskIdRequest.getId();

        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Задачи с таким id не существует");
        }
    }
}
