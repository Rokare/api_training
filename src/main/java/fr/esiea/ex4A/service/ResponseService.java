package fr.esiea.ex4A.service;

import fr.esiea.ex4A.agify.AgifyResponse;
import fr.esiea.ex4A.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }
    public boolean addResponse(AgifyResponse agifyResponse) {
        return this.responseRepository.getAllUsers().add(agifyResponse);
    }
    public ArrayList<AgifyResponse> getAllResponses() {
        return this.responseRepository.getAllUsers();
    }

    public AgifyResponse getResponse(String name, String country_id) {
        Optional<AgifyResponse> agifyResponse = this.responseRepository.getAllUsers().stream().filter(x -> x.getName().equals(name) && x.getCountry_id().equals(country_id)).findFirst();
        return agifyResponse.isEmpty() ? null : agifyResponse.get();
    }
}
