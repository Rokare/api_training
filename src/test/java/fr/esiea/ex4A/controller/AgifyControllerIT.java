package fr.esiea.ex4A.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esiea.ex4A.entity.Match;
import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@EnableWebMvc
public class AgifyControllerIT {
    private final MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Mock
    AgifyController agifyControllerMock;
    AgifyControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void signUp() throws Exception {
        User user = new User("test@test.com", "test","test","FR", "F", "F");
        when(agifyControllerMock.signUp(any(User.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(user));
        userService.addUser(user);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/api/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                           "userEmail":"test@test.com",
                            "userName":"test",
                            "userTwitter":"test",
                            "userCountry":"FR",
                            "userSex":"F",
                            "userSexPref":"F"
                        }
                        """));
        verify(userService,times(1)).addUser(any(User.class));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void checkMatches() throws Exception {
        ArrayList<User> listUser = new ArrayList<>();
        listUser.add(new User(new User("test@test.com", "test","test","FR", "F", "F"), 25));
        listUser.add(new User(new User("test2@test2.com", "test2","test2","FR", "F", "F"), 27));
        listUser.add(new User(new User("test3@test3.com", "test3","test3","FR", "F", "F"), 24));

        when(userService.getAllUsers()).thenReturn(listUser);
        when(userService.getUser(any(String.class))).thenReturn(new User(new User("test@test.com", "test","test","FR", "F", "F"), 25));
        when(agifyControllerMock.checkMatches("test", "FR")).thenReturn(ResponseEntity.status(HttpStatus.ACCEPTED).body(List.of(new Match("test2", "test2"))));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/matches?userName=test&userCountry=FR"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "name":"test2",
                                "twitter":"test2"
                            },
                            {
                                "name":"test3",
                                "twitter":"test3"
                            }
                        ]
                        """));

        Assertions.assertEquals(userService.getAllUsers().size(),3);
    }
}