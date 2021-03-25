package fr.esiea.ex4A.agify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgifyResponseTest {
    AgifyResponse agifyResponse = new AgifyResponse("test", 25, 300, "FR");

    @Test
    void getName() {
        Assertions.assertEquals("test", agifyResponse.getName());
    }

    @Test
    void getAge() {
        Assertions.assertEquals(25, agifyResponse.getAge());
    }

    @Test
    void getCount() {
        Assertions.assertEquals(300, agifyResponse.getCount());
    }

    @Test
    void getCountry_id() {
        Assertions.assertEquals("FR", agifyResponse.getCountry_id());
    }
}