package ru.shapovalov.coursework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shapovalov.coursework.auth.UserService;
import ru.shapovalov.coursework.models.Nutritionist;
import ru.shapovalov.coursework.repositories.NutritionistRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NutritionistService {
    private final NutritionistRepository nutritionistRepository;

    private final UserService userService;

    @Autowired
    public NutritionistService(NutritionistRepository nutritionistRepository, UserService userService) {
        this.nutritionistRepository = nutritionistRepository;
        this.userService = userService;
    }

    public List<Nutritionist> getAllNut() {
        return nutritionistRepository.findAll();
    }

    public Nutritionist getNutritionist(int id) {
        return nutritionistRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<String> saveNutritionist(Nutritionist nutritionist,
                                                   HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        nutritionistRepository.save(nutritionist);
        return new ResponseEntity<>("Save successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateNut(int id, Nutritionist nutritionist, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        Nutritionist nutritionistToUpdate = getNutritionist(id);
        nutritionistToUpdate.setFullName(nutritionist.getFullName());
        nutritionistToUpdate.setAcademicRank(nutritionist.getAcademicRank());
        return new ResponseEntity<>("Update successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteNut(int id, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        nutritionistRepository.deleteById(id);
        return new ResponseEntity<>("Delete successfull", HttpStatus.OK);
    }
}
