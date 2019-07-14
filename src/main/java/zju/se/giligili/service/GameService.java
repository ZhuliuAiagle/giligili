package zju.se.giligili.service;

import zju.se.giligili.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> findAllByName(String name);
    Optional<Game> findOneById(String id);
    List<Game> findAllByIntroduction(String key);
}
