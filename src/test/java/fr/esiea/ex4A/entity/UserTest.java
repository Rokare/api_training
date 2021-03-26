package fr.esiea.ex4A.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {
    private final User user;
    private final User userWithAge;

    UserTest() {
        this.user =  new User("test@test.com", "test", "test", "FR","F", "H" );
        this.userWithAge = new User(user, 25);
    }

    @Test
    void getUserEmail() {
        Assertions.assertEquals("test@test.com", this.user.getUserEmail());
    }

    @Test
    void getUserName() {
        Assertions.assertEquals("test", this.user.getUserName());
    }

    @Test
    void getUserTwitter() {
        Assertions.assertEquals("test", this.user.getUserTwitter());
    }

    @Test
    void getUserCountry() {
        Assertions.assertEquals("FR", this.user.getUserCountry());
    }

    @Test
    void getUserSex() {
        Assertions.assertEquals(User.Sex.F, this.user.getUserSex());
    }

    @Test
    void getUserSexPref() {
        Assertions.assertEquals(User.Sex.H, this.user.getUserSexPref());
    }

    @Test
    void getAge() {
        Assertions.assertEquals(25,this.userWithAge.getAge().get());
    }
}