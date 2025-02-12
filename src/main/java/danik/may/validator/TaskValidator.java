package danik.may.validator;

import danik.may.dto.request.task.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Проверяет на валидность входные данные задач
 */
@Component
@RequiredArgsConstructor
public class TaskValidator {
    /**
     * Проверят, есть ли в запросе на обновление задачи поля, изменять которые разрешено только админу
     * @param request запрос
     * @return результат проверки
     */
    public static boolean isValidForImplementer(UpdateTaskRequest request) {
        boolean isValid = (request.getHeader() != null
                || request.getDescription() != null
                || request.getPriority() != null
                || request.getAuthor() != null
                || request.getImplementer() != null);

        return !isValid;
    }
}
