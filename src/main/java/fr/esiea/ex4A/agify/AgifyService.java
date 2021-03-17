/* (C)2021 */
package fr.esiea.ex4A.agify;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AgifyService {
    @GET("/")
    Call<AgifyResponse> getResponce(
            @Query("name") String name, @Query("country_id") String country_id);

}


