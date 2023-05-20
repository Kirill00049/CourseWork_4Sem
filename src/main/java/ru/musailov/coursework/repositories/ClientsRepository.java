package ru.musailov.coursework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.musailov.coursework.models.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Integer> {
}
