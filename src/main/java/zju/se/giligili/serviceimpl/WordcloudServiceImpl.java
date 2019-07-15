package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import zju.se.giligili.dao.WordcloudDao;
import zju.se.giligili.model.Wordcloud;
import zju.se.giligili.service.WordcloudService;


@Repository("wordcloudService")
public class WordcloudServiceImpl implements WordcloudService {
    @Autowired
    private WordcloudDao wordcloudDao;
    public Wordcloud getByName(String name){
        return wordcloudDao.getCloudByName(name);
    }
}
