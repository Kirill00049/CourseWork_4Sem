package ru.shapovalov.coursework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.coursework.models.Nutritionist;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {
}
