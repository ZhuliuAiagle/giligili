package zju.se.giligili.dao;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import zju.se.giligili.model.Game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"should\": {\n" +
            "            \"multi_match\": {\n" +
            "                \"query\": \"?0\",\n" +
            "                \"type\": \"best_fields\",\n" +
            "                \"fields\": [\"name^5\", \"introduction^2\", \"description^2\", " +
            "                               \"type^2\", \"theme^2\", \"mode^2\"],\n" +
            "                \"tie_breaker\": 0.6\n" +
            "            }\n" +
            "        },\n" +
            "        \"must\": [?1],\n" +
            "        \"filter\": {\n" +
            "            \"range\": {\"startDate\" : {?2} }\n" +
            "        }\n" +
            "    }   \n" +
            "}")
    Page<Game> searchByConditions(String key, String conditions, String filter, Pageable pageable);

    @Query("{\"term\": {\"name.keyword\": \"?0\"}}")
    Game searchByName(String name);


}
