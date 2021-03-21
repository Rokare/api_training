package fr.esiea.ex4A.repository;

import fr.esiea.ex4A.agify.AgifyResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ResponseRepository {
    private ArrayList<AgifyResponse> responses = new ArrayList<>();
    public ArrayList<AgifyResponse> getAllUsers() {
        return responses;
    }
}
