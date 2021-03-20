/* (C)2021 */
package fr.esiea.ex4A.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

public class User {
    private final String userEmail;
    private final String userName;
    private final String userTwitter;
    private final String userCountry;
    private final Sex userSex;
    private final Sex userSexPref;
    private final Optional<Integer> age;

    public enum Sex {

        F("F"),
        H("H"),
        O("O");
        Sex(String f) {}
    }

    public User(@NotEmpty @JsonProperty("userEmail") String userEmail, @NotEmpty @JsonProperty("userName") String userName,
                @NotEmpty @JsonProperty("userTwitter") String userTwitter, @NotEmpty @JsonProperty("userCountry") String userCountry,
                @JsonProperty("userSex") String userSex, @JsonProperty("userSexPref") String userSexPref) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTwitter = userTwitter;
        this.userCountry = userCountry;
        this.userSex = Sex.valueOf(userSex);
        this.userSexPref = Sex.valueOf(userSexPref);
        this.age = Optional.empty();
    }

    public User(User user, int age) {
        this.userEmail = user.userEmail;
        this.userName = user.userName;
        this.userTwitter = user.userTwitter;
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

    public String getUserTwitter() {
        return userTwitter;
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


    @Override
    public String toString() {
        return "User{" +
                "userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userTweeter='" + userTwitter + '\'' +
                ", userCountry='" + userCountry + '\'' +
                ", userSex=" + userSex +
                ", userSexPref=" + userSexPref +
                ", age=" + age +
                '}';
    }
}
