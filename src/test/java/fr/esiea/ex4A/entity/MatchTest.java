package fr.esiea.ex4A.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    Match match = new Match("test", "test");
    @Test
    void getName() {
        Assertions.assertEquals("test", match.getName());
    }

    @Test
    void getTwitter() {
        Assertions.assertEquals("test", match.getTwitter());
    }
}