package fr.esiea.ex4A.service;

import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean addUser(User user) {
        return this.userRepository.getUserRepository().add(user);
    }
    public User getUser(String username) {
        for (User user : this.userRepository.getUserRepository()){
            if(user.getUserName().equals(username))
                return user;
        }
        return null;
    }
    public ArrayList<User> getAllUsers() {
        return this.userRepository.getUserRepository();
    }
}
