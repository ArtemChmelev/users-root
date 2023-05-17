package ru.chmelev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.chmelev.dto.contacts.request.ContactsRequestDto;
import ru.chmelev.dto.contacts.response.ContactsResponseDto;
import ru.chmelev.entity.Contacts;
import ru.chmelev.entity.Users;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repository.ContactsRepository;
import ru.chmelev.repository.UsersRepository;
import ru.chmelev.service.ContactsService;

import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class ContactsServiceImpl implements ContactsService {

    private final ModelMapper modelMapper;
    private final ContactsRepository contactsRepository;
    private final UsersRepository usersRepository;

    @Override
    public ContactsResponseDto create(UUID id, ContactsRequestDto contactsRequestDto) {
        Users usersFromDb = usersRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Пользователь c id: [%s] не найден".formatted(id)));

        Contacts request = modelMapper.map(contactsRequestDto, Contacts.class);
        request.setId(usersFromDb.getId());
        Contacts saved = contactsRepository.save(request);

        return modelMapper.map(saved, ContactsResponseDto.class);

    }

    @Override
    public ContactsResponseDto update(UUID id, ContactsRequestDto contactsRequestDto) {
        Contacts forUpdate = contactsRepository.findById(id).orElseThrow(
                () -> new NoFoundException("У пользователя c id: [%s] нет контактной информации".formatted(id)));

        Contacts contactsForSave = modelMapper.map(contactsRequestDto, Contacts.class);
        contactsForSave.setId(forUpdate.getId());
        Contacts updated = contactsRepository.save(contactsForSave);

        return modelMapper.map(updated, ContactsResponseDto.class);
    }

    @Override
    public ContactsResponseDto findById(UUID id) {
        Contacts contacts = contactsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("У пользователя с id [%s]  нет контактов".formatted(id)));

        return modelMapper.map(contacts, ContactsResponseDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        contactsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("У пользователя с id [%s]  нет контактов".formatted(id)));
        contactsRepository.deleteById(id);
    }
}
