package fr.esiea.ex4A.service;

import fr.esiea.ex4A.agify.AgifyClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class AgifyServiceTest {

    private AgifyClient createClient(String url) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        return retrofit.create(AgifyClient.class);
    }
    @Test
    void getAge() {
        AgifyService service = new AgifyService(createClient("https://api.agify.io"));
        int expectedAge = 25;
        String username = "Maxime";
        String countryId = "FR";
        int age = service.getAge(username, countryId);
        Assertions.assertNotEquals(age, 0);
        Assertions.assertEquals(age, expectedAge);
    }
}