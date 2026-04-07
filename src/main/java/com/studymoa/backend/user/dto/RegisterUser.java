package com.studymoa.backend.user.dto;

import lombok.Data;

@Data
public class RegisterUser {
    private String loginId;
    private String name;
    private String email;
    private String password;
}
