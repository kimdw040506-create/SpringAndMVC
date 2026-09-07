package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(long id);

    void save(User user);

    void update(User user);

    void delete(long id);
}