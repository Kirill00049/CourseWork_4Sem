package ru.musailov.coursework.repositories;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.musailov.coursework.models.Nutritionist;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {
}
