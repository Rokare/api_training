package fr.esiea.ex4A.controller;

import fr.esiea.ex4A.service.AgifyService;
import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class AgifyController {
    @Autowired
    private final UserService userService;
    private final AgifyService agifyService;
    public AgifyController(UserService userService, AgifyService agifyService) {
        this.userService = userService;
        this.agifyService = agifyService;
    }
    @PostMapping(path = "/inscription")
    ResponseEntity<Object> signUp(@RequestBody @Validated User user) {
        int age = agifyService.getAge(user.getUserName(), user.getUserCountry());
        if(userService.addUser(new User(user, age)))
            return ResponseEntity.status(201).body(user);
        return ResponseEntity.status(404).body("Page not Found");
    }

    @GetMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> checkMatches(@RequestParam(name = "userName") String userName,
        @RequestParam(name = "userCountry") String userCountry) {
        int age = userService.getUser(userName).getAge().get();
        List<User> listUser = userService.getAllUsers().stream().filter(x -> x.getAge().get() <= age -4 || x.getAge().get() >= age + 4 ).collect(Collectors.toList());
        if(!listUser.isEmpty())
            return ResponseEntity.status(200).body(listUser);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
    }
}
