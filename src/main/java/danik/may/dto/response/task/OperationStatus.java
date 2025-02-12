package danik.may.dto.response.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Подкласс для JSON-response. Хранит данные об успехе операции или возникшей ошибке, при работе задачами.
 */
@NoArgsConstructor
@Getter
@Setter
public class OperationStatus {
    /**
     * Результат операции
     */
    private boolean success;

    /**
     * Данные о случившейся ошибке
     */
    private Error error;
}
