package zju.se.giligili.dao;

import zju.se.giligili.model.Game;

public interface GameDao {
    Game findOneByName(String Name);
}
