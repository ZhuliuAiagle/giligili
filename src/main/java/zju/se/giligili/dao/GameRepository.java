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
            "    \"minimum_should_match\": \"80%\"," +
            "    \"tie_breaker\": 0.6\n" +
            "   }" +
            "}")
    Page<Game> searchLazy(String key, Pageable pageable);

    @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"must\": [{\n" +
            "            \"multi_match\": {\n" +
            "                \"query\": \"?0\",\n" +
            "                \"type\": \"best_fields\",\n" +
            "                \"fields\": [\"name^5\", \"introduction^2\", \"description^2\", " +
            "                               \"type^2\", \"theme^2\", \"mode^2\"],\n" +
            "                \"minimum_should_match\": \"80%\"," +
            "                \"tie_breaker\": 0.4\n" +
            "            }\n" +
            "        }?1],\n" +
//            "        \"must\": [?1],\n" +
            "        \"filter\": {\n" +
            "            \"range\": {\"startDate\" : {?2} }\n" +
            "        }\n" +
            "    }   \n" +
            "}")
    Page<Game> searchByConditions(String key, String conditions, String filter, Pageable pageable);

    @Query("{\n" +
            "    \"bool\": {\n" +
            "        \"must\": [?1],\n" +
            "        \"filter\": {\n" +
            "            \"range\": {\"startDate\" : {?2} }\n" +
            "        }\n" +
            "    }   \n" +
            "}")
    Page<Game> searchOnlyByConditions(String key, String conditions, String filter, Pageable pageable);

    @Query("{\"term\": {\"name.keyword\": \"?0\"}}")
    Game searchByName(String name);


}
