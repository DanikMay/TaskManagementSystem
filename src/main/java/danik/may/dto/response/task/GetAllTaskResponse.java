package danik.may.dto.response.task;

import danik.may.dto.response.task.op_status.OperationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GetAllTaskResponse {
    private OperationStatus operationStatus;
    private List<TaskBody> body;
    public GetAllTaskResponse(List<TaskBody> body) {
        this.body = body;
    }
}
