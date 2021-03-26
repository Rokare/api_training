package fr.esiea.ex4A.service;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.agify.AgifyResponse;
import fr.esiea.ex4A.repository.ResponseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AgifyServiceTest {

    private AgifyClient getAgifyClientMock() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.agify.io").addConverterFactory(GsonConverterFactory.create()).build();
        NetworkBehavior networkBehavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build();
        BehaviorDelegate<AgifyClient> behaviorDelegate = mockRetrofit.create(AgifyClient.class);

        return new AgifyClientMock(behaviorDelegate);
    }

    private class AgifyClientMock implements AgifyClient {
         private final BehaviorDelegate<AgifyClient> behaviorDelegate;
         public AgifyClientMock(BehaviorDelegate<AgifyClient> behaviorDelegate) {
             this.behaviorDelegate = behaviorDelegate;
         }

         @Override
         public Call<AgifyResponse> getResponce(String name, String country_id) {
             AgifyResponse agifyResponse = new AgifyResponse("André", 44, 507, "FR");
             return behaviorDelegate.returningResponse(agifyResponse).getResponce(name, country_id);
         }
    }

    @Mock
    private final AgifyService agifyService;
    @Mock
    private final ResponseRepository responseRepository = new ResponseRepository();
    @Mock
    private final ResponseService responseService = new ResponseService(responseRepository);

    AgifyServiceTest() {
        this.agifyService = new AgifyService(getAgifyClientMock(), responseService);
    }

    @Test
    void GetAgeOfAndré() throws IOException {
        assertThat(agifyService.getAge("André", "FR")).isEqualTo(44);
    }

}