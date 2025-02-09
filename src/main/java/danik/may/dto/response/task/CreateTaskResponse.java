package danik.may.dto.response.task;

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
