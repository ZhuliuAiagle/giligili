package zju.se.giligili.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import zju.se.giligili.model.Game;

import java.util.List;
import java.util.Optional;

@Repository("gameRepo")
public interface GameRepository extends ElasticsearchRepository<Game, String> {
    List<Game> findAllByName(String name);
    List<Game> findAllByIntroduction(String key);
    Optional<Game> findById(String id);
}
