/* (C)2021 */
package fr.esiea.ex4A.repository;

import fr.esiea.ex4A.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserRepository {
    public ArrayList<User> users = new ArrayList<>();
}
