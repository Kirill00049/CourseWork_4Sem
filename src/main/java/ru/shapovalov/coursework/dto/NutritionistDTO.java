package ru.shapovalov.coursework.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class NutritionistDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "full_name")
    private String fullName;


    @Column(name = "academic_rank")
    private String academicRank;
}
