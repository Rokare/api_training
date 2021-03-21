package fr.esiea.ex4A.service;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.agify.AgifyResponse;
import org.springframework.stereotype.Service;
import retrofit2.Call;
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
        Call<AgifyResponse> call = agifyClient.getResponce(userName, countryId);
        try {
            Response<AgifyResponse> response = call.execute();
            if (response.isSuccessful()) {
                AgifyResponse result = response.body();
                responseService.addResponse(result);
                age = result.getAge();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return age;
    }
}
