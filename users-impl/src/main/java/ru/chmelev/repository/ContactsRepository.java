package ru.chmelev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chmelev.entity.Contacts;

import java.util.UUID;

// Репозиторий для работы с контактной информацией
@Repository
public interface ContactsRepository extends JpaRepository<Contacts, UUID> {
    // TODO: 17.05.2023  Рассмотреть использование библиотеки jooq
}
