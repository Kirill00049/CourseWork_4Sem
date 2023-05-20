package ru.musailov.coursework.auth.dto;
import lombok.Data;


@Data
public class AuthenticationRequestDto {
    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;
}
