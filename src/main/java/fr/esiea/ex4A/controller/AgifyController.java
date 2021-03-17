package fr.esiea.ex4A.controller;

import fr.esiea.ex4A.agify.AgifyResponse;
import fr.esiea.ex4A.agify.AgifyService;
import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;

@RequestMapping("/api")
@RestController
public class AgifyController {
    @Autowired
    private final UserRepository userRepository;
    private final AgifyService agifyService;
    public AgifyController(UserRepository userRepository, AgifyService agifyService) {
        this.userRepository = userRepository;
        this.agifyService = agifyService;
    }
    @PostMapping(path = "/inscription")
    ResponseEntity<Object> signUp(@RequestBody @Validated User user) {
        //        if (body.containsKey("userEmail") && body.containsKey("userName")
        //            && body.containsKey("userTweeter") && body.containsKey("userCountry")
        //            && body.containsKey("userSex") && body.containsKey("userSexPref")){
        //            if(body.get("userSex").matches("^[OMF]$") &&
        // body.get("userSexPref").matches("^[OMF]$")) {
        //                return ResponseEntity.status(201).body(body);
        //            }
        //        }
        if (userRepository.users.add(user)) return ResponseEntity.status(201).body(user);
        return ResponseEntity.status(404).body("Page not Found");
    }

    @GetMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> checkMatches(
        @RequestParam(name = "userName") String userName,
        @RequestParam(name = "userCountry") String userCountry) {
        try {
            Response<AgifyResponse> response =
                agifyService.getResponce(userName, userCountry).execute();
            if (response.isSuccessful()) {
                AgifyResponse result = response.body();
                System.out.println(result);
                return ResponseEntity.status(200).body(result);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
    }
}