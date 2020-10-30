package com.manager.service;

import com.manager.model.Note;
import com.manager.model.Task;
import com.manager.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Map<Integer, User> users = new HashMap<>();
    private Integer id = 0;

    public void autoIncrement() {
        id = id + 1;
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User addUser(User user) {
        autoIncrement();
        user.setId(id);
        users.put(id, user);
        return user;
    }

}
