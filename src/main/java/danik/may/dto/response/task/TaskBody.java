package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskBody {
    private int id;
    private String header;
    private String description;
    private String status;
    private String priority;
    private String author;
    private String implementer;
}
