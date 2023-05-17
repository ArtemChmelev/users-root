package ru.chmelev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chmelev.entity.Users;

import java.util.UUID;

// Репозиторий для работы с пользователями
@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    // TODO: 17.05.2023  Рассмотреть использование библиотеки jooq
}
