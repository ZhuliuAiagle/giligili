package zju.se.giligili.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import zju.se.giligili.model.Game;

import java.util.List;

@Repository("gameRepo")
public interface GameRepository extends ElasticsearchRepository<Game, String> {
    List<Game> findAllByName(String name);
    List<Game> findAllByNameOrIntroductionOrDescription(String key);
}
