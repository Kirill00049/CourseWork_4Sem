package ru.shapovalov.coursework.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Nutritionist")
@Setter
@Getter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Nutritionist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "full_name")
    private String fullName;


    @Column(name = "academic_rank")
    private String academicRank;


    @OneToMany(mappedBy = "nutritionist", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Client> clients;

    public Nutritionist(String fullName, String academicRank) {
        this.fullName = fullName;
        this.academicRank = academicRank;
    }
}