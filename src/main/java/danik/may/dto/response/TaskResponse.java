package danik.may.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponse {
    private boolean success;
    private Error error;
    private TaskBody body;
}
