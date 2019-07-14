package zju.se.giligili.service;

import org.springframework.data.domain.Page;
import zju.se.giligili.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> findAllByName(String name);
    Optional<Game> findOneById(String id);
    List<Game> findAllByIntroduction(String key);
    List<Game> searchLazy(String key);
    Page<Game> searchByConditions(String key, String type, String theme, String mode, int page);
}
