package fr.esiea.ex4A.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.service.AgifyService;
import fr.esiea.ex4A.service.UserService;
import org.junit.jupiter.api.Test;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@EnableWebMvc
public class AgifyControllerIT {
    private final MockMvc mockMvc;
    @MockBean
    private AgifyService agifyService;
    @MockBean
    private UserService userService;
    @MockBean
    private AgifyController agifyControllerMock;
    AgifyControllerIT(@Autowired MockMvc mockMvc ) {
        this.mockMvc = mockMvc;
    }

    @Test
    void signUp() throws Exception {
        User user = new User("test@test.com", "test","test","FR", "F", "F");
        when(agifyControllerMock.signUp(any(User.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(user));
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
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void checkMatches() {
    }
}