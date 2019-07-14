package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.NewsRespository;
import zju.se.giligili.model.News;
import zju.se.giligili.service.NewsService;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRespository newsRespository;

    @Override
    public List<News> findAllByName(String name) {
        return newsRespository.findAllByName(name);
    }


}
