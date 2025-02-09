package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateTaskResponse {
    private OperationStatus operationStatus;
    public UpdateTaskResponse(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
