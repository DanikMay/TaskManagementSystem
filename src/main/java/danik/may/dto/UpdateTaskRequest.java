package danik.may.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateTaskRequest {
    private int id;
    private String header;
    private String description;
    private String status;
    private String priority;
    private String author;
    private String executor;
}
