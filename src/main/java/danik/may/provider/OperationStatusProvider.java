package danik.may.provider;

import danik.may.dto.response.op_status.Error;
import danik.may.dto.response.op_status.OperationStatus;

public class OperationStatusProvider {
    public static OperationStatus getSuccessOp() {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(true);

        return operationStatus;
    }

    public static OperationStatus getDataBaseErrorOp(int id) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(false);

        Error error = new Error();
        error.setTitle("Ошибка базы данных");
        error.setText(String.format("Задача с id: %d не найдена", id));
        operationStatus.setError(new Error());

        return operationStatus;
    }

    public static OperationStatus getInvalidTaskRequestErrorOp(String userName) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(false);

        Error error = new Error();
        error.setTitle("Ошибка валидации");
        error.setText(String.format("У пользователя с именем: %s нет прав на обновление полей: " +
                "header, description, priority, author, executor", userName));
        operationStatus.setError(error);

        return operationStatus;
    }

    public static OperationStatus getAccessErrorOp(String userName, int id) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setSuccess(false);

        Error error = new Error();
        error.setTitle("Ошибка доступа");
        error.setText(String.format("У пользователя с именем: %s нет права на доступ к задаче с id: %d", userName, id));
        operationStatus.setError(error);

        return operationStatus;
    }
}
