package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DeleteTaskResponse {
    private OperationStatus operationStatus;

    public DeleteTaskResponse(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
