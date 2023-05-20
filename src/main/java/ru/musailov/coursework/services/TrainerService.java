package ru.musailov.coursework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.musailov.coursework.auth.UserService;
import ru.musailov.coursework.models.Trainer;
import ru.musailov.coursework.repositories.TrainerRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    private final UserService userService;


    @Autowired
    public TrainerService(TrainerRepository trainerRepository, UserService userService) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
    }

    public List<Trainer> getTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainer(int id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<String> saveTrainer(Trainer trainer, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        trainerRepository.save(trainer);
        return new ResponseEntity<>("Save successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteTrainer(int id, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        if (trainerRepository.existsById(id)) {
            trainerRepository.deleteById(id);
        }
        return new ResponseEntity<>("Delete successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateTrainer(int id, Trainer trainer, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        Trainer trainerToUpdate = getTrainer(id);
        trainerToUpdate.setFullName(trainer.getFullName());
        trainerToUpdate.setWorkExp(trainer.getWorkExp());
        trainerToUpdate.setRank(trainer.getRank());
        trainerToUpdate.setDateOfBirth(trainer.getDateOfBirth());
        trainerRepository.save(trainerToUpdate);
        return new ResponseEntity<>("Update successfull", HttpStatus.OK);
    }

}
