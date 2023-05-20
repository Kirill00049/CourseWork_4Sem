package ru.musailov.coursework.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.musailov.coursework.dto.ClientDTO;
import ru.musailov.coursework.dto.TrainerDTO;
import ru.musailov.coursework.models.Client;
import ru.musailov.coursework.models.Trainer;
import ru.musailov.coursework.services.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainersController {
    private final TrainerService trainerService;

    @Autowired
    public TrainersController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public List<Trainer> getTrainers() {
        return trainerService.getTrainers();
    }

    @GetMapping("/{id}")
    public Trainer getTrainer(@PathVariable("id") int id) {
        return trainerService.getTrainer(id);
    }

    @PostMapping()
    public ResponseEntity<String> saveTrainer(@RequestBody @Valid TrainerDTO trainerDTO,
                                                 @NonNull HttpServletRequest request,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }


        return trainerService.saveTrainer(convertToTrainer(trainerDTO), request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrainer(@PathVariable("id") int id,
                                                    HttpServletRequest request) {
        return trainerService.deleteTrainer(id, request);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTrainer(@PathVariable("id") int id,
                                                   @RequestBody @Valid TrainerDTO trainerDTO,
                                                   HttpServletRequest request,
                                                   BindingResult bindingResult) {

        return trainerService.updateTrainer(id, convertToTrainer(trainerDTO), request);

    }

    public Trainer convertToTrainer(TrainerDTO trainerDTO) {
        Trainer trainer = new Trainer();
        trainer.setFullName(trainerDTO.getFullName());
        trainer.setRank(trainerDTO.getRank());
        trainer.setWorkExp(trainerDTO.getWorkExp());
        trainer.setDateOfBirth(trainerDTO.getDateOfBirth());

        return trainer;
    }
}
