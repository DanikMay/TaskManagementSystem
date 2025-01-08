package danik.may.dto.response.task.op_status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OperationStatus {
    private boolean success;
    private Error error;
}
