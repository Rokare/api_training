package fr.esiea.ex4A.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private final Match match;

    MatchTest() {
        this.match = new Match("test", "test");
    }
    @Test
    void getName() {
        Assertions.assertEquals("test", this.match.getName());
    }

    @Test
    void getTwitter() {
        Assertions.assertEquals("test", this.match.getTwitter());
    }
}