package zju.se.giligili.service;

import zju.se.giligili.model.Game;

import java.util.List;

public interface GameService {
    List<Game> findAllByName(String name);
    List<Game> findAllByNameOrIntroductionOrDescription(String key);
}
