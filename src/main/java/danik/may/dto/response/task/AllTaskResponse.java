package danik.may.dto.response.task;

import danik.may.dto.response.op_status.OperationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AllTaskResponse {
    private OperationStatus operationStatus;
    private List<TaskBody> body;
    public AllTaskResponse(List<TaskBody> body) {
        this.body = body;
    }
}
