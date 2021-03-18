package fr.esiea.ex4A.service;

import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceTest {
    private final UserService userService = new UserService(new UserRepository());
    @Test
    void addUser() {

        String username = "test";
        User user = new User("test@test.com", username, "test","FR", "F", "F");
        
        Assertions.assertTrue(userService.addUser(user));
        Assertions.assertEquals(userService.getUser(username), user);
    }

    @Test
    void getAllUsers() {
        User user1 = new User("test@test.com", "test", "test","FR", "F", "F");
        User user2 = new User("test2@test2.com", "test2", "test2","FR", "F", "F");
        List<User> listUsers = List.of(
                user1,user2
        );
        userService.addUser(user1);
        userService.addUser(user2);

        Assertions.assertEquals(userService.getAllUsers(), listUsers);
    }
}