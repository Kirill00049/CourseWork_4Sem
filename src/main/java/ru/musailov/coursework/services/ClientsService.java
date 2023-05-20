package ru.musailov.coursework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.musailov.coursework.auth.UserService;
import ru.musailov.coursework.models.Client;
import ru.musailov.coursework.repositories.ClientsRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientsService {

    private final ClientsRepository clientsRepository;

    private final UserService userService;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository, UserService userService) {
        this.clientsRepository = clientsRepository;
        this.userService = userService;
    }

    public List<Client> getAll() {
        return clientsRepository.findAll();
    }

    public Client getClient(int id) {
        return clientsRepository.findById(id).orElse(null);
    }

    @Transactional
    public ResponseEntity<String> saveClient(Client client, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        clientsRepository.save(client);
        return new ResponseEntity<>("Save successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateClient(int id, Client client, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        Client clientToUpdate = getClient(id);
        clientToUpdate.setNutritionist(client.getNutritionist());
        clientToUpdate.setTrainer(client.getTrainer());
        clientToUpdate.setFullName(client.getFullName());
        clientsRepository.save(clientToUpdate);

        return new ResponseEntity<>("Update successfull", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> deleteClient(int id, HttpServletRequest request) {
        if (!userService.checkAdmin(request)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: you don't have enough authorities");
        }
        clientsRepository.deleteById(id);
        return new ResponseEntity<>("Delete successfull", HttpStatus.OK);
    }

}
