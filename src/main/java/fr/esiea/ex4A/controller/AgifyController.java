package fr.esiea.ex4A.controller;

import fr.esiea.ex4A.agify.AgifyResponse;
import fr.esiea.ex4A.entity.Match;
import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.service.AgifyService;
import fr.esiea.ex4A.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<Object> signUp(@RequestBody @Valid User user) {
        int age = 0;
        AgifyResponse agifyResponse = agifyService.getResponseService().getResponse(user.getUserName(), user.getUserCountry());
        if (agifyResponse == null)
            age = agifyService.getAge(user.getUserName(), user.getUserCountry());
        else
            age = agifyResponse.getAge();
        User completeUser = new User(user, age);
        if(userService.getAllUsers().add(completeUser)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(completeUser);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error happened during registration");
    }

    @GetMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> checkMatches(@RequestParam(name = "userName") String userName, @RequestParam(name = "userCountry") String userCountry) {
        int age = userService.getUser(userName).getAge().get();
        User.Sex sexPref = userService.getUser(userName).getUserSexPref();
        List<User> listUsers = userService.getMatchUsers(userName, age, sexPref);
        List<Match> listMatches = new ArrayList<>();
        listUsers.forEach(x -> listMatches.add(new Match(x.getUserName(), x.getUserTwitter())));
        if(!listMatches.isEmpty()) {
            return ResponseEntity.status(200).body(listMatches);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not Found");
    }
}
