/* (C)2021 */
package fr.esiea.ex4A.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class User {
    private final String userEmail;
    private final String userName;
    private final String userTweeter;
    private final String userCountry;
    private final Sex userSex;
    private final Sex userSexPref;
    private final Optional<Integer> age;

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
        this.age = Optional.empty();
    }

    public User(User user, int age) {
        this.userEmail = user.userEmail;
        this.userName = user.userName;
        this.userTweeter = user.userTweeter;
        this.userCountry = user.userCountry;
        this.userSex = user.userSex;
        this.userSexPref = user.userSexPref;
        this.age = Optional.of(age);
    }
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTweeter() {
        return userTweeter;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public Sex getUserSex() {
        return userSex;
    }

    public Sex getUserSexPref() {
        return userSexPref;
    }

    public Optional<Integer> getAge() {
        return age;
    }
}
