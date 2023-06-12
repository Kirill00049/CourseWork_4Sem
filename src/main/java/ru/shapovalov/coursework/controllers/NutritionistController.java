package ru.shapovalov.coursework.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shapovalov.coursework.dto.NutritionistDTO;
import ru.shapovalov.coursework.models.Nutritionist;
import ru.shapovalov.coursework.services.NutritionistService;

import java.util.List;

@RestController
@RequestMapping("/nutritionists")
public class NutritionistController {
    private final NutritionistService nutritionistService;

    @Autowired
    public NutritionistController(NutritionistService nutritionistService) {
        this.nutritionistService = nutritionistService;
    }

    @GetMapping()
    public List<Nutritionist> getNutritionists(HttpServletRequest request) {
        return nutritionistService.getAllNut();
    }

    @GetMapping("/{id}")
    public Nutritionist getOneNut(@PathVariable("id") int id) {
        return nutritionistService.getNutritionist(id);
    }

    @PostMapping
    public ResponseEntity<String> saveNut(@RequestBody @Valid NutritionistDTO nutritionistDTO,
                                              HttpServletRequest request,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }

        return nutritionistService.saveNutritionist(convertToNutritionist(nutritionistDTO), request);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateNut(@PathVariable("id") int id,
                                                @RequestBody @Valid NutritionistDTO nutritionistDTO,
                                                HttpServletRequest request,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }

        return nutritionistService.updateNut(id, convertToNutritionist(nutritionistDTO), request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNut(@PathVariable("id") int id,
                                                HttpServletRequest request) {
        return nutritionistService.deleteNut(id, request);

    }


    public Nutritionist convertToNutritionist(NutritionistDTO nutritionistDTO) {
        Nutritionist nutritionist = new Nutritionist();

        nutritionist.setFullName(nutritionistDTO.getFullName());
        nutritionist.setAcademicRank(nutritionistDTO.getAcademicRank());

        return nutritionist;
    }

}
