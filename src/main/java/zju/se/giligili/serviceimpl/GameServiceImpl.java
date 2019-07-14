package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.GameDao;
import zju.se.giligili.dao.GameRepository;
import zju.se.giligili.model.Game;
import zju.se.giligili.service.GameService;

import java.util.List;
import java.util.Optional;

@Service("gameService")
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAllByName(String name) {
        return gameRepository.findAllByName(name);
    }
    @Override
    public Optional<Game> findOneById(String id){
        return gameRepository.findById(id);
    }
    @Override
    public List<Game> findAllByIntroduction(String key) {
        return gameRepository.findAllByIntroduction(key);
    }

    @Override
    public List<Game> searchLazy(String key) {
        return gameRepository.searchLazy(key);
    }

    @Override
    public List<Game> searchByConditions(String key, String type, String theme, String mode) {
        String conditions = null;
        return gameRepository.searchByConditions(key, conditions);
    }

}
