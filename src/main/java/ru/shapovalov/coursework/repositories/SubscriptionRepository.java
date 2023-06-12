package ru.shapovalov.coursework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.coursework.models.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
