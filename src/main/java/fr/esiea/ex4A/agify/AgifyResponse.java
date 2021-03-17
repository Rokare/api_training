/* (C)2021 */
package fr.esiea.ex4A.agify;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AgifyResponse {

    private final String name;
    private final int age;
    private final int count;
    private final String country_id;

    @JsonCreator
    public AgifyResponse(
            @JsonProperty("name") String name,
            @JsonProperty("age") int age,
            @JsonProperty("count") int count,
            @JsonProperty("country_id") String country_id) {

        this.name = name;
        this.age = age;
        this.count = count;
        this.country_id = country_id;
    }
}
