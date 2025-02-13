package danik.may.repository;

import danik.may.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репо для работы с данными пользователей в таблице в БД
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Находит данные 0 пользователе по его имени
     *
     * @param username - имя
     * @return данные о пользователе
     */
    Optional<User> findByUsername(String username);

    /**
     * Проверяет, существует ли пользователь с заданным именем
     *
     * @param username имя
     * @return результат проверки
     */
    boolean existsByUsername(String username);

    /**
     * Проверяет, существует ли пользователь с заданным адресом почты
     *
     * @param email адрес почты
     * @return результат проверки
     */
    boolean existsByEmail(String email);
}