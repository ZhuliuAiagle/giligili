package zju.se.giligili.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import zju.se.giligili.dao.WordcloudDao;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.model.Wordcloud;


@Repository("wordcloudDao")
public class WordCloudDaoImpl implements WordcloudDao {
    @Autowired
    MongoTemplate mongoTemplate;
    public Wordcloud getCloudByName(String name){
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Wordcloud.class);
    }
}
