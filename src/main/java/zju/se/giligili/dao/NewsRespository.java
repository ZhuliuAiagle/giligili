package zju.se.giligili.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import zju.se.giligili.model.News;

import java.util.List;

@Repository
public interface NewsRespository extends ElasticsearchRepository<News, String> {
    List<News> findAllByName(String name);
}
