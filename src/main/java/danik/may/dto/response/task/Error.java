package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Подкласс для JSON-response. Хранит данные о возникшей ошибке при работе с задачами
 */
@NoArgsConstructor
@Getter
@Setter
public class Error {
    private String title;
    private String text;
}
