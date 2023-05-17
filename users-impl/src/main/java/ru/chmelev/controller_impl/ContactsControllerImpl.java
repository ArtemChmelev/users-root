package ru.chmelev.controller_impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.chmelev.controller.ContactsController;
import ru.chmelev.dto.contacts.request.ContactsRequestDto;
import ru.chmelev.dto.contacts.response.ContactsResponseDto;
import ru.chmelev.service.ContactsService;

import java.net.URI;
import java.util.UUID;

//Реализация контроллера контактной информации
@RestController
@AllArgsConstructor
@Slf4j
public class ContactsControllerImpl implements ContactsController {

    private final ContactsService contactsService;

    @Override
    public ResponseEntity<?> create(UUID id, ContactsRequestDto contactsRequestDto) {
        log.info("Request for creating contacts with inDto: {} for userId: {}", contactsRequestDto, id);
        ContactsResponseDto createdUserInfo = contactsService.create(id, contactsRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(createdUserInfo);
    }

    @Override
    public ResponseEntity<?> update(UUID id, ContactsRequestDto contactsRequestDto) {
        log.info("Request for update contacts with inDto: {} for userId: {}", contactsRequestDto, id);
        ContactsResponseDto updatedContactsInfo = contactsService.update(id, contactsRequestDto);
        return ResponseEntity.ok(updatedContactsInfo);
    }

    @Override
    public ResponseEntity<?> findById(UUID id) {
        log.info("Request for reading contacts by id: {}", id);
        ContactsResponseDto foundContactsInfo = contactsService.findById(id);
        return ResponseEntity.ok(foundContactsInfo);
    }

    @Override
    public ResponseEntity<?> deleteById(UUID id) {
        log.info("Request for deleted contacts by id: {}", id);
        contactsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
