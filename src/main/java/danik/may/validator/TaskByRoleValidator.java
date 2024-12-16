package danik.may.validator;

import danik.may.dto.UpdateTaskRequest;
import danik.may.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static danik.may.entity.Role.ROLE_USER;

@Component
@RequiredArgsConstructor
public class TaskByRoleValidator {
    private final UserService userService;
    public final String SUCCESS = "Разрешение получено\n";

    public String getPermission(UpdateTaskRequest request) {
        boolean isUserRole = userService.getCurrentUser().getRole().equals(ROLE_USER);
        boolean isProtectedFields = (request.getHeader() != null
                || request.getDescription() != null
                || request.getPriority() != null
                || request.getAuthor() != null
                || request.getExecutor() != null);

        if (isUserRole && isProtectedFields) {
            return String.format("У пользователя с именем %s нет прав на обновление полей:\n" +
                            "header, description, priority, author, executor\n",
                    userService.getCurrentUser().getUsername());
        } else {
            return SUCCESS;
        }
    }
}
