package ru.shapovalov.coursework.auth;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shapovalov.coursework.auth.dto.AuthenticationRequestDto;
import ru.shapovalov.coursework.auth.dto.RegistrationUserDto;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationRestController authenticationRestController;
    @Autowired
    public UserController(UserService userService, AuthenticationRestController authenticationRestController) {
        this.userService = userService;
        this.authenticationRestController = authenticationRestController;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<String> getUserById(@PathVariable(name = "id") Long id,
                                              @NonNull HttpServletRequest request){

        return userService.getUser(id, request);
    }
    @GetMapping()
    public ResponseEntity<String> getAllUsers(@NonNull HttpServletRequest request){
        return userService.getAll(request);
    }
    @PostMapping
    public ResponseEntity<String> registryAdmin(@RequestBody RegistrationUserDto registrationUserDto,
                                                @NonNull HttpServletRequest request){
        ResponseEntity<String> answer = userService.registryAdmin(registrationUserDto.toUser(), request);
        if (answer.getStatusCode().equals(HttpStatus.FORBIDDEN))return answer;
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto(registrationUserDto.getUsername(),
                registrationUserDto.getPassword());
        return authenticationRestController.login(requestDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id,@NonNull HttpServletRequest request ){
        return userService.delete(id, request);
    }

}
