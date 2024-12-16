package danik.may.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {
    private int id;
    private String header;
    private String description;
    private String status;
    private String priority;
    private String author;
    private String executor;
}
