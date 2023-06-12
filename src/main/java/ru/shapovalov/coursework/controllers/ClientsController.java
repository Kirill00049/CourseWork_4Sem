package ru.shapovalov.coursework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shapovalov.coursework.dto.ClientDTO;
import ru.shapovalov.coursework.models.Client;
import ru.shapovalov.coursework.models.Nutritionist;
import ru.shapovalov.coursework.models.Trainer;
import ru.shapovalov.coursework.services.ClientsService;
import ru.shapovalov.coursework.services.NutritionistService;
import ru.shapovalov.coursework.services.TrainerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    private final ClientsService clientsService;

    private final NutritionistService nutritionistService;

    private final TrainerService trainerService;

    @Autowired
    public ClientsController(ClientsService clientsService, NutritionistService nutritionistService, TrainerService trainerService) {
        this.clientsService = clientsService;
        this.nutritionistService = nutritionistService;
        this.trainerService = trainerService;
    }

    @GetMapping()
    public List<Client> getClients() {
        return clientsService.getAll();
    }

    @GetMapping("/{id}")
    public Client getPerson(@PathVariable("id") int id) {
        return clientsService.getClient(id);
    }

    @PostMapping()
    public ResponseEntity<String> saveClient(@RequestBody @Valid ClientDTO clientDTO,
                                                 HttpServletRequest request,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }

        return clientsService.saveClient(convertToClient(clientDTO), request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") int id,
                                               HttpServletRequest request) {
        return clientsService.deleteClient(id, request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable("id") int id,
                                                   @RequestBody @Valid ClientDTO clientDTO,
                                                   HttpServletRequest request,
                                                   BindingResult bindingResult) {

        return clientsService.updateClient(id, convertToClient(clientDTO), request);
    }

    public Client convertToClient(ClientDTO clientDTO) {
        Client client = new Client();

        client.setFullName(clientDTO.getFullName());
        client.setDateOfBirth(clientDTO.getDateOfBirth());

        Trainer trainer = trainerService.getTrainer(clientDTO.getTrainerId());
        Nutritionist nutritionist = nutritionistService.getNutritionist(clientDTO.getNutritionistId());

        client.setTrainer(trainer);
        client.setNutritionist(nutritionist);

        System.out.println(client);
        return client;
    }
}
