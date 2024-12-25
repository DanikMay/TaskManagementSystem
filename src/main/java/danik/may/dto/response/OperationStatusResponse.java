package danik.may.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OperationStatusResponse {
    private boolean success;
    private Error error;
}
