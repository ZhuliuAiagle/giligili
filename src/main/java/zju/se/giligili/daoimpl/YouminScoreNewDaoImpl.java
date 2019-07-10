package zju.se.giligili.daoimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import zju.se.giligili.dao.YouminScoreNewDao;
import zju.se.giligili.model.YouminScoreNew;

@Repository("youminScoreNewDao")
public class YouminScoreNewDaoImpl implements YouminScoreNewDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public YouminScoreNew getScoreByName(String name){
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), YouminScoreNew.class);
    }
}
