package ru.shapovalov.coursework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shapovalov.coursework.auth.UserService;
import ru.shapovalov.coursework.models.Subscription;
import ru.shapovalov.coursework.repositories.SubscriptionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    private final UserService userService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
    }

    public List<Subscription> getSubs() {
        return subscriptionRepository.findAll();
    }

    public Subscription getSubById(int id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<String> saveSub(Subscription subscription,
                                          HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        if (subscription.getClient() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No client with that id");
        }
        subscriptionRepository.save(subscription);

        return new ResponseEntity<>("Save successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateSub(int id, Subscription subscription,
                                            HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        Subscription subscription1 = getSubById(id);
        subscription1.setSubscriptionType(subscription.getSubscriptionType());
        subscription1.setExpirationDate(subscription.getExpirationDate());
        System.out.println(subscription1);
        subscriptionRepository.save(subscription1);
        return new ResponseEntity<>("Update successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteSub(int id, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        subscriptionRepository.deleteById(id);
        return new ResponseEntity<>("Update successfull", HttpStatus.OK);
    }
}
