package ru.musailov.coursework.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.musailov.coursework.auth.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static RegistrationUserDto fromUser(User user) {
        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setId(user.getId());
        registrationUserDto.setUsername(user.getUsername());
        registrationUserDto.setFirstName(user.getFirstName());
        registrationUserDto.setLastName(user.getLastName());
        registrationUserDto.setEmail(user.getEmail());
        registrationUserDto.setPassword(user.getPassword());
        return registrationUserDto;
    }
}
