package ru.shapovalov.coursework.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trainer")
@Setter
@Getter
@NoArgsConstructor

public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Дата рождения не должна быть пустой")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "work_experience")
    private int workExp;

    @Column(name = "rank")
    private String rank;
    @JsonManagedReference
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Client> clients;

    public Trainer(String fullName, String dateOfBirth, int workExp, String rank) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.workExp = workExp;
        this.rank = rank;
    }
}
