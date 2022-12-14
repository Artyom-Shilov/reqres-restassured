package com.shilov.training.dataproviders;

import com.shilov.training.models.User;
import com.shilov.training.validation.Validator;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@UtilityClass
public class UsersReader {

    private static final Properties USERS_PROPERTIES = PropertiesLoader.loadProperties(
            "src/test/resources/users.properties");

    private static final long CURRENT_USERS_AMOUNT;

    static {
        CURRENT_USERS_AMOUNT = USERS_PROPERTIES.entrySet()
                .stream()
                .filter(e -> ((String)e.getKey()).contains("email"))
                .count();
    }

    public static User getUserById(long id) {
        Validator.checkId(id, CURRENT_USERS_AMOUNT);
        return User.builder()
                .id(id)
                .email(USERS_PROPERTIES.getProperty("user." + id + ".email"))
                .firstName(USERS_PROPERTIES.getProperty("user." + id + ".first_name"))
                .lastName(USERS_PROPERTIES.getProperty("user." + id + ".last_name"))
                .avatar(USERS_PROPERTIES.getProperty("user." + id + ".avatar"))
                .build();
    }

    public static List<User> getUsersInRange(long from, long to) {
       Validator.checkIdInRange(from, to, CURRENT_USERS_AMOUNT);
        List<User> users = new ArrayList<>();
        for (long i = from; i <= to; i++ ) {
            users.add(getUserById(i));
        }
        return users;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (long i = 1; i <= CURRENT_USERS_AMOUNT; i++) {
            users.add(getUserById(i));
        }
        return users;
    }
}
