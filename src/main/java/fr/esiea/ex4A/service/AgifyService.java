package fr.esiea.ex4A.service;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.agify.AgifyResponse;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

@Service
public class AgifyService {
    private final AgifyClient agifyClient;
    private final ResponseService responseService;
    public AgifyService(AgifyClient agifyClient, ResponseService responseService){
        this.agifyClient = agifyClient;
        this.responseService = responseService;
    }
    public ResponseService getResponseService() {
        return this.responseService;
    }
    public int getAge(String userName, String countryId) {
        int age = 0;
        try {
            Response<AgifyResponse> response = agifyClient.getResponce(userName, countryId).execute();
            if (response.isSuccessful()) {
                responseService.addResponse(response.body());
                age = response.body().getAge();
            }
        } catch (IOException ex){ ex.printStackTrace(); }
        return age;
    }
}
