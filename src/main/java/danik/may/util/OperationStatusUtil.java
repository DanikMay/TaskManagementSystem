package danik.may.util;

import danik.may.dto.response.task.Error;
import danik.may.dto.response.task.OperationStatus;

/**
 * Создаёт шаблоны для разных исходов операций в TaskService
 */
public class OperationStatusUtil {
    /**
     * Операция прошла успешно
     * @return результат операции
     */
    public static OperationStatus getSuccessOp() {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(true);

        return operationStatus;
    }

    /**
     * В ходе операции возникла ошибка, задачи с заданным id не существует
     * @param id id
     * @return результат операции
     */
    public static OperationStatus getDataBaseErrorOp(int id) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(false);

        Error error = new Error();
        error.setTitle("Ошибка базы данных");
        error.setText(String.format("Задача с id: %d не найдена", id));
        operationStatus.setError(error);

        return operationStatus;
    }

    /**
     * В ходе операции возникла ошибка, пользователь-исполнитель пытался обновить защищённые поля
     * @param userName имя пользователя
     * @return результат операции
     */
    public static OperationStatus getInvalidTaskRequestErrorOp(String userName) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(false);

        Error error = new Error();
        error.setTitle("Ошибка валидации");
        error.setText(String.format("У пользователя с именем: %s нет прав на обновление полей: " +
                "header, description, priority, author, implementer", userName));
        operationStatus.setError(error);

        return operationStatus;
    }

    /**
     * В ходе операции возникла ошибка, пользователь с ролью юзер, пытался получить доступ к задаче, хотя он не является её исполнителем
     * @param userName имя пользователя
     * @param id id
     * @return результат операции
     */
    public static OperationStatus getAccessErrorOp(String userName, int id) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(false);

        Error error = new Error();
        error.setTitle("Ошибка доступа");
        error.setText(String.format("У пользователя с именем: %s нет прав на доступ к задаче с id: %d", userName, id));
        operationStatus.setError(error);

        return operationStatus;
    }
}
