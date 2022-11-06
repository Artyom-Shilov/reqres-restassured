package com.shilov.training.requestsbodies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterUserRequest {

    private String username;

    private String email;

    private String password;
}
