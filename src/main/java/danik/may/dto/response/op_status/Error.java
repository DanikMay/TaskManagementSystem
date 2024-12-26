package danik.may.dto.response.op_status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Error {
    private String title;
    private String text;
}
