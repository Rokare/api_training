/* (C)2021 */
package fr.esiea.ex4A;

import fr.esiea.ex4A.agify.AgifyService;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SpringBootApplication
public class Launcher {
    @Bean(name = "AgifyService")
    public AgifyService agifyClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://api.agify.io")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();

        return retrofit.create(AgifyService.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }
}
