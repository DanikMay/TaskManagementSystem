package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные о результате обновления задачи, для формирования JSON-response
 */
@NoArgsConstructor
@Getter
@Setter
public class UpdateTaskResponse {
    /**
     * Данные об успехе операции или случившейся ошибки
     */
    private OperationStatus operationStatus;

    public UpdateTaskResponse(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
