package com.andersen.banking.service.registry.meeting_db.entities;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;

    private String username;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String email;

    private String phone;

    private String password;
}
