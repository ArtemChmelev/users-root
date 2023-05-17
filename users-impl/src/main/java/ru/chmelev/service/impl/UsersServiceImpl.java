package ru.chmelev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chmelev.dto.contacts.request.ContactsRequestDto;
import ru.chmelev.dto.user.request.UserCreateDto;
import ru.chmelev.dto.user.request.UserUpdateDto;
import ru.chmelev.dto.user.response.UserResponseDto;
import ru.chmelev.entity.Contacts;
import ru.chmelev.entity.Users;
import ru.chmelev.exception.NoFoundException;
import ru.chmelev.repository.ContactsRepository;
import ru.chmelev.repository.UsersRepository;
import ru.chmelev.service.UsersService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final ModelMapper modelMapper;
    private final UsersRepository usersRepository;
    private final ContactsRepository contactsRepository;

    @Transactional
    @Override
    public UserResponseDto create(UserCreateDto userCreateDto) {
        Users savedUser = saveUser(userCreateDto);

        saveContacts(userCreateDto, savedUser);

        return modelMapper.map(savedUser, UserResponseDto.class);
    }



    private Users saveUser(UserCreateDto userCreateDto) {
        Users userForSave = modelMapper.map(userCreateDto, Users.class);
        return usersRepository.save(userForSave);
    }

    private void saveContacts(UserCreateDto userCreateDto, Users savedUser) {
        if (userCreateDto.getContacts() != null) {
            ContactsRequestDto contacts = userCreateDto.getContacts();
            Contacts contactsForSave = modelMapper.map(contacts, Contacts.class);
            contactsForSave.setId(savedUser.getId());
            Contacts savedContacts = contactsRepository.save(contactsForSave);
            savedUser.setContacts(savedContacts);
        }
    }

    @Override
    public UserResponseDto update(UUID id, UserUpdateDto userUpdateDto) {
        Users userFromDb = usersRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Пользователь c id: [%s] не найден".formatted(id)));

        Users userForUpdate = modelMapper.map(userUpdateDto, Users.class);
        userForUpdate.setId(userFromDb.getId());
        Users updatedUser = usersRepository.save(userForUpdate);

        return modelMapper.map(updatedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto findById(UUID id) {
        Users users = usersRepository.findById(id).
                orElseThrow(() -> new NoFoundException("Пользователь c id: [%s] не найден".formatted(id)));
        return modelMapper.map(users, UserResponseDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        usersRepository.findById(id).
                orElseThrow(() -> new NoFoundException("Пользователь c id: [%s] не найден".formatted(id)));
        usersRepository.deleteById(id);
    }

    @Override
    public Page<UserResponseDto> findAll(Pageable pageable) {
        Page<Users> all = usersRepository.findAll(pageable);
        List<UserResponseDto> userResponseDtos = all.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class)).toList();

        return new PageImpl<>(userResponseDtos, all.getPageable(), all.getTotalElements());
    }
}

