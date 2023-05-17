package ru.chmelev.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.chmelev.dto.contacts.request.ContactsRequestDto;
import ru.chmelev.dto.contacts.response.ContactsResponseDto;

import java.util.UUID;

public interface ContactsService {
    ContactsResponseDto create(UUID id, ContactsRequestDto contactsRequestDto);

    ContactsResponseDto update(UUID id, ContactsRequestDto contactsRequestDto);

    ContactsResponseDto findById(UUID id);

    void deleteById(UUID id);
}
