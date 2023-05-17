package ru.chmelev.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

//Сущность контактной информации
@Data
@Entity
@Table(name = "contacts")
public class Contacts {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

}
