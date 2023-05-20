package ru.musailov.coursework.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.musailov.coursework.dto.SubscriptionDTO;
import ru.musailov.coursework.models.Client;
import ru.musailov.coursework.models.Subscription;
import ru.musailov.coursework.services.ClientsService;
import ru.musailov.coursework.services.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/subs")
public class SubscriptionsController {

    private final SubscriptionService subscriptionService;

    private final ClientsService clientsService;

    @Autowired
    public SubscriptionsController(SubscriptionService subscriptionService, ClientsService clientsService) {
        this.subscriptionService = subscriptionService;
        this.clientsService = clientsService;
    }

    @GetMapping
    public List<Subscription> getSubs() {
        return subscriptionService.getSubs();
    }

    @PostMapping
    public ResponseEntity<String> saveSub(@RequestBody @Valid SubscriptionDTO subscriptionDTO,
                                              HttpServletRequest request,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }

        return subscriptionService.saveSub(convertToSub(subscriptionDTO), request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSub(@PathVariable("id") int id,
                                                HttpServletRequest request) {

        return subscriptionService.deleteSub(id, request);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateSub(@PathVariable("id") int id,
                                                @RequestBody @Valid SubscriptionDTO subscriptionDTO,
                                                HttpServletRequest request,
                                                BindingResult bindingResult) {
        return subscriptionService.updateSub(id, convertToSub(subscriptionDTO), request);

    }

    public Subscription convertToSub(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        System.out.println(subscriptionDTO);
        Client client = clientsService.getClient(subscriptionDTO.getClientId());

        subscription.setSubscriptionType(subscriptionDTO.getSubscriptionType());
        subscription.setExpirationDate(subscriptionDTO.getExpirationDate());
        subscription.setClient(client);



        return subscription;
    }
}
