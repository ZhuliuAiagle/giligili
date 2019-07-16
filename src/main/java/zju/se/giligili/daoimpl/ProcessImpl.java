package zju.se.giligili.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.ProcessDao;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.model.Process;


@Service("processDao")
public class ProcessImpl implements ProcessDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Process getByName(String name){
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Process.class);
    }
}
