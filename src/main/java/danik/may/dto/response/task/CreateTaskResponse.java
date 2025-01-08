package danik.may.dto.response.task;

import danik.may.dto.response.task.op_status.OperationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateTaskResponse {
    private OperationStatus operationStatus;

    public CreateTaskResponse(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
