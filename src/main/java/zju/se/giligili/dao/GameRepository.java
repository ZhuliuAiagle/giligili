package zju.se.giligili.dao;

import org.springframework.data.elasticsearch.annotations.Query;
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

    @Query("{" +
            "\"multi_match\": {\n" +
            "    \"query\": \"?0\",\n" +
            "    \"type\": \"best_fields\",\n" +
            "    \"fields\": [\n" +
            "        \"name^5\",\n" +
            "        \"introduction^2\",\n" +
            "        \"description^2\"\n" +
            "    ],\n" +
            "    \"tie_breaker\": 0.6\n" +
            "   }" +
            "}")
    List<Game> searchLazy(String key);

    @Query()
    List<Game> searchByConditions(String key, String conditions);
}
