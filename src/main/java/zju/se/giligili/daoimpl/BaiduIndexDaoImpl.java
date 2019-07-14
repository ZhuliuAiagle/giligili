package zju.se.giligili.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import zju.se.giligili.dao.BaiduIndexDao;
import zju.se.giligili.model.BaiduIndex;
import org.bson.types.ObjectId;

@Repository("baiduIndexDao")
public class BaiduIndexDaoImpl implements BaiduIndexDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaiduIndex getIndex(String name) {
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), BaiduIndex.class);
    }

    @Override
    public BaiduIndex findIndexById(String id) {
        return mongoTemplate.findById(id, BaiduIndex.class);
    }

}
