package fr.esiea.ex4A.hello;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
class HelloController {

    private final HelloRepository helloRepository;

    HelloController(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    HelloData sayHello(@RequestParam(name = "name", required = false) String name) {
        final HelloData helloData;
        if (name == null || name.isBlank()) {
            helloData = helloRepository.randomHello();
        } else {
            helloData = helloRepository.getHelloFor(name);
        }
        return helloData;
    }

    @PostMapping(path = "/api/inscription")
    ResponseEntity<Object> signUp(@RequestBody Map<String,String> body) {
        if (body.containsKey("userEmail") && body.containsKey("userName")
            && body.containsKey("userTweeter") && body.containsKey("userCountry")
            && body.containsKey("userSex") && body.containsKey("userSexPref")){
            if(body.get("userSex").matches("^[OMF]$") && body.get("userSexPref").matches("^[OMF]$")) {
                return ResponseEntity.status(201).body(body);
            }
        }
        return ResponseEntity.status(404).body("Page not Found");
    }

    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> checkMatches(@RequestParam(name = "userName") String userName, @RequestParam(name = "userCountry") String userCountry) {
        final List<Map<String, String>> profiles = List.of(
            Map.of("name", "gregre","twitter","gregre"),
            Map.of("name","gregre2", "twitter", "gregre2"),
            Map.of("name","gregre3","twitter","gregre3"),
            Map.of("name","gregre4", "twitter", "gregre4")
        );
        return ResponseEntity.ok().body(profiles.get(new Random().nextInt(profiles.size())));
    }
}
