package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные о результате удаления задачи, для формирования JSON-response
 */
@NoArgsConstructor
@Getter
@Setter
public class DeleteTaskResponse {
    /**
     * Данные об успехе операции или случившейся ошибки
     */
    private OperationStatus operationStatus;

    public DeleteTaskResponse(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
