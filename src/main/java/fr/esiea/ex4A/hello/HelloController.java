/* (C)2021 */
package fr.esiea.ex4A.hello;

import fr.esiea.ex4A.agify.AgifyService;
import fr.esiea.ex4A.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @Autowired private UserRepository userRepository;

    private final AgifyService agifyService;

    private final HelloRepository helloRepository;

    HelloController(AgifyService agifyService, HelloRepository helloRepository) {
        this.agifyService = agifyService;
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
}
