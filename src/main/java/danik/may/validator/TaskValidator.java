package danik.may.validator;

import danik.may.dto.request.task.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskValidator {
    public static boolean isValid(UpdateTaskRequest request, boolean isAdmin) {
        boolean isProtectedFields = (request.getHeader() != null
                || request.getDescription() != null
                || request.getPriority() != null
                || request.getAuthor() != null
                || request.getImplementer() != null);
        boolean valid = isAdmin || !isProtectedFields;

        return valid;
    }
}
