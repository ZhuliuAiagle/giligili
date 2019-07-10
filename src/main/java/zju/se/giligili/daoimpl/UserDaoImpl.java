package zju.se.giligili.daoimpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import zju.se.giligili.dao.UserDao;
import zju.se.giligili.model.User;


@Repository("userDao")
public class UserDaoImpl implements UserDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> findAll(){
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User getUser(Integer id){
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),User.class);
    }
    @Override
    public void insert(User user){
        mongoTemplate.insert(user);
    }
    @Override
    public void remove(Integer id){
        Criteria criteria = Criteria.where("id").is(id);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, User.class);
    }
    @Override
    public List<User> findByPage(User user, Pageable pageable) {
        Query query = new Query();
        if (user != null && user.getName() != null) {
            //模糊查询
            query = new Query(Criteria.where("name").regex("^" + user.getName()));
        }
        List<User> list = mongoTemplate.find(query.with(pageable), User.class);
        return list;
    }
    @Override
    public void update(User user) {
        Criteria criteria = Criteria.where("id").is(user.getId());
        Query query = new Query(criteria);
        Update update = Update.update("name", user.getName()).set("age", user.getAge());
        mongoTemplate.updateMulti(query, update, User.class);
    }
    @Override
    public void insertAll(List<User> users) {
        mongoTemplate.insertAll(users);
    }
}
