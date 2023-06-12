package ru.shapovalov.coursework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.coursework.models.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
