package danik.may.dto.response.task;

import danik.may.dto.response.op_status.OperationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {
    private OperationStatus operationStatus;
    private TaskBody body;
    public TaskResponse(TaskBody body) {
        this.body = body;
    }
}
