package zju.se.giligili.dao;

import java.util.List;
import org.springframework.data.domain.Pageable;
import zju.se.giligili.model.User;


public interface UserDao {
    List<User> findAll();
    User getUser(Integer id);
    void update(User user);
    void insert(User user);
    void insertAll(List<User> users);
    void remove(Integer id);
    List<User> findByPage(User user, Pageable pageable);
}
