package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
    public Page<Game> searchByConditions(String key, String type, String theme, String mode, int page) {
        String conditions = "";
        Pageable pageable = PageRequest.of((page - 1), 10, Sort.Direction.DESC, "youminData.userScore");
        String format = "{\"match\":{\"%s.keyword\":\"%s\"}}";
        if(type != null && !type.equals("")){
            conditions += String.format(format,"type",type);
            conditions += ",";
        }
        if(theme != null && !theme.equals("")){
            conditions += String.format(format,"theme",theme);
            conditions += ",";
        }
        if(mode != null && !mode.equals("")){
            conditions += String.format(format,"mode",mode);
            conditions += ",";
        }
        if(!conditions.equals("")) {
            StringBuilder b = new StringBuilder(conditions);
            b.setLength(b.length() - 1);
            conditions = b.toString();
        }
        System.out.println("11"+conditions);
        return gameRepository.searchByConditions(key, conditions, pageable);
    }

}
