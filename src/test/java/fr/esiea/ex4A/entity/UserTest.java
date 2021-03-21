package fr.esiea.ex4A.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {
    User user = new User("test@test.com", "test", "test", "FR","F", "H" );
    User completeUser = new User(user, 25);
    @Test
    void getUserEmail() {
        Assertions.assertEquals("test@test.com", completeUser.getUserEmail());
    }

    @Test
    void getUserName() {
        Assertions.assertEquals("test", completeUser.getUserName());
    }

    @Test
    void getUserTwitter() {
        Assertions.assertEquals("test", completeUser.getUserTwitter());
    }

    @Test
    void getUserCountry() {
        Assertions.assertEquals("FR", completeUser.getUserCountry());
    }

    @Test
    void getUserSex() {
        Assertions.assertEquals(User.Sex.F, completeUser.getUserSex());
    }

    @Test
    void getUserSexPref() {
        Assertions.assertEquals(User.Sex.H, completeUser.getUserSexPref());
    }

    @Test
    void getAge() {
        Assertions.assertEquals(25,completeUser.getAge().get());
    }
}