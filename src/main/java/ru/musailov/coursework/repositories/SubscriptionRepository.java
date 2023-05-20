package ru.musailov.coursework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.musailov.coursework.models.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
