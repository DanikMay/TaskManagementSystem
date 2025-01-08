package danik.may.dto.request.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateTaskRequest {
    private int id;
    private String header;
    private String description;
    private String status;
    private String priority;
    private String author;
    private String implementer;
}
