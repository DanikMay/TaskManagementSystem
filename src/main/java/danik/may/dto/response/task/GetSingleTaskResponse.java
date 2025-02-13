package danik.may.dto.response.task;

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
