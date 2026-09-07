package hiber.repository;

import hiber.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {

        return entityManager
                .createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public User findById(long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {

        entityManager.persist(user);
    }

    @Override
    public void update(User user) {

        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {

        User user = entityManager.find(User.class, id);

        if (user != null) {
            entityManager.remove(user);
        }
    }
}