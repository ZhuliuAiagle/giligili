package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.GameDao;
import zju.se.giligili.model.Game;
import zju.se.giligili.service.GameService;

@Service("gameService")
public class GameServiceImpl implements GameService {
    @Autowired
    private GameDao gameDao;
    @Override
    public Game getGameByName(String name){
        return gameDao.findOneByName(name);
    }
}
