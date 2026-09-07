package hiber.repository;

import hiber.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(long id);

    void save(User user);

    void update(User user);

    void delete(long id);
}