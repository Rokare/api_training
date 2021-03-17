/* (C)2021 */
package fr.esiea.ex4A.repository;

import fr.esiea.ex4A.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRepository {
    private final ArrayList<User> users = new ArrayList<>();
    public ArrayList<User> getUserRepository(){
        return this.users;
    }
}
