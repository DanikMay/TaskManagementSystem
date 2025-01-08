package danik.may.dto.response.task;

import danik.may.dto.response.task.op_status.OperationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GetSingleTaskResponse {
    private OperationStatus operationStatus;
    private TaskBody body;
    public GetSingleTaskResponse(TaskBody body) {
        this.body = body;
    }
}
