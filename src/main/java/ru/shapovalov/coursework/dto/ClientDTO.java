package ru.shapovalov.coursework.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class ClientDTO {

    private int trainerId;

    private int nutritionistId;
    @NotEmpty(message = "Имя не должно быть пустым")
    private String fullName;

    @NotEmpty(message = "Дата рождения не должна быть пустой")
    private String dateOfBirth;


    @Override
    public String toString() {
        return "ClientDTO{" +
                "trainerId=" + trainerId +
                ", nutritionistId=" + nutritionistId +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
