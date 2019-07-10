package zju.se.giligili.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import zju.se.giligili.dao.GameDao;
import zju.se.giligili.model.Game;


@Repository("gameDao")
public class GameDaoImpl implements GameDao{
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Game findOneByName(String name){
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Game.class);
    }
}
