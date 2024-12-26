package danik.may.validator;

import danik.may.dto.request.task.TaskUpdateRequest;
import danik.may.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static danik.may.entity.Role.ROLE_USER;

@Component
@RequiredArgsConstructor
public class TaskValidator {
    public static boolean getPermissionToUpdate(TaskUpdateRequest request, Role role) {
        boolean isUserRole = role.equals(ROLE_USER);
        boolean isProtectedFields = (request.getHeader() != null
                || request.getDescription() != null
                || request.getPriority() != null
                || request.getAuthor() != null
                || request.getImplementer() != null);

        boolean success;
        if (isUserRole && isProtectedFields) {
            success = false;
        } else {
            success = true;
        }
        return success;
    }
}
