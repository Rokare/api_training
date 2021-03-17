/* (C)2021 */
package fr.esiea.ex4A.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class User {
    private final String userEmail;
    private final String userName;
    private final String userTweeter;
    private final String userCountry;
    private final Sex userSex;
    private final Sex userSexPref;

    private enum Sex {
        F("F"),
        M("M"),
        O("");

        Sex(String f) {}
    }

    public User(@NotEmpty @JsonProperty("userEmail") String userEmail, @NotEmpty @JsonProperty("userName") String userName,
                @NotEmpty @JsonProperty("userTweeter") String userTweeter, @NotEmpty @JsonProperty("userCountry") String userCountry,
                @JsonProperty("userSex") String userSex, @JsonProperty("userSexPref") String userSexPref) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTweeter = userTweeter;
        this.userCountry = userCountry;
        this.userSex = Sex.valueOf(userSex);
        this.userSexPref = Sex.valueOf(userSexPref);
    }
}
