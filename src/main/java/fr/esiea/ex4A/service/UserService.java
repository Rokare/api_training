package fr.esiea.ex4A.service;

import fr.esiea.ex4A.entity.User;
import fr.esiea.ex4A.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUser(String username) {
        for (User user : this.userRepository.getUserRepository()){
            if(user.getUserName().equals(username))
                return user;
        }
        return null;
    }
    public List<User> getMatchUsers(String userName, int age, User.Sex sexPref) {
        return this.getAllUsers().stream()
                .filter(x -> x.getAge().get() <= age + 4 && x.getAge().get() >= age - 4
                        && !x.getUserName().equals(userName) && x.getUserSex().equals(sexPref)).collect(Collectors.toList());
    }
    public ArrayList<User> getAllUsers() {
        return this.userRepository.getUserRepository();
    }
}
