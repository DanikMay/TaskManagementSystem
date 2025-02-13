package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные о результате создания задачи, для формирования JSON-response
 */
@NoArgsConstructor
@Getter
@Setter
public class CreateTaskResponse {
    /**
     * Данные об успехе операции или случившейся ошибки
     */
    private OperationStatus operationStatus;

    public CreateTaskResponse(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
