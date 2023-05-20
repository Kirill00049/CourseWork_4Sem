package ru.musailov.coursework.models;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    @JsonBackReference
    private Trainer trainer;


    @ManyToOne
    @JoinColumn(name = "nutritionist_id", referencedColumnName = "id")
    @JsonBackReference
    private Nutritionist nutritionist;


    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Дата рождения не должна быть пустой")
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToOne(mappedBy = "client")
    @JsonBackReference
    private Subscription subscription;

    public Client(Trainer trainer, Nutritionist nutritionist, String fullName, String dateOfBirth) {
        this.trainer = trainer;
        this.nutritionist = nutritionist;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }
}
