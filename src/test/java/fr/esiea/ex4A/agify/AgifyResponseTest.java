package fr.esiea.ex4A.agify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgifyResponseTest {
    private final AgifyResponse agifyResponse;

    AgifyResponseTest() {
        this.agifyResponse =  new AgifyResponse("test", 25, 300, "FR");
    }

    @Test
    void getName() {
        Assertions.assertEquals("test", this.agifyResponse.getName());
    }

    @Test
    void getAge() {
        Assertions.assertEquals(25, this.agifyResponse.getAge());
    }

    @Test
    void getCount() {
        Assertions.assertEquals(300, this.agifyResponse.getCount());
    }

    @Test
    void getCountry_id() {
        Assertions.assertEquals("FR", this.agifyResponse.getCountry_id());
    }
}