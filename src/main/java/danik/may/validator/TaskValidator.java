package danik.may.validator;

import danik.may.dto.request.TaskRequest;
import danik.may.dto.response.Error;
import danik.may.dto.response.OperationStatusResponse;
import danik.may.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static danik.may.entity.Role.ROLE_USER;

@Component
@RequiredArgsConstructor
public class TaskValidator {
    private final UserService userService;
    private final OperationStatusResponse operationStatus = new OperationStatusResponse();

    public OperationStatusResponse getPermissionForUpdate(TaskRequest request) {
        boolean isUserRole = userService.getCurrentUser().getRole().equals(ROLE_USER);
        boolean isProtectedFields = (request.getHeader() != null
                || request.getDescription() != null
                || request.getPriority() != null
                || request.getAuthor() != null
                || request.getImplementer()!= null);

        if (isUserRole && isProtectedFields) {
            Error error = new Error();
            error.setTitle("Ошибка валидации");
            error.setText(String.format("У пользователя с именем %s нет прав на обновление полей: " +
                            "header, description, priority, author, executor", userService.getCurrentUser().getUsername()));
            operationStatus.setSuccess(false);
            operationStatus.setError(error);
        } else {
            operationStatus.setSuccess(true);
        }
        return operationStatus;
    }
}
