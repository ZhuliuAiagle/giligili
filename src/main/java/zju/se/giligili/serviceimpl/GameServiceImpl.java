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

import java.util.*;

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
    public Page<Game> searchByConditions(String key, String type, String theme, String mode, String year, int page, int isOrdered) {
        String conditions = "";
        String filter = "";
        Pageable pageable = PageRequest.of((page - 1), 10, Sort.Direction.DESC, "avgScore");
        Pageable defaultPageble = PageRequest.of((page - 1), 10);
        String format       = "{\"match\":{\"%s.keyword\":\"%s\"}}";
        String formatYear   = "\"gt\":\"%s\",\"lt\":\"%s\"";
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
        if(!year.equals("")) {
            String a = year + "-01-01";
            String b = year + "-12-31";
            filter = String.format(formatYear, a, b);
        }
        System.out.println("11"+conditions);
        // 选择是默认搜索还是排序后搜索
        if(isOrdered == 1)
            return gameRepository.searchByConditions(key, conditions, filter, pageable);
        else
            return gameRepository.searchByConditions(key, conditions, filter, defaultPageble);
    }
    @Override
    public List<Game> getCompetitors(String id) {
        Optional<Game> mainGameOpt = findOneById(id);
        try {
            Game mainGame = mainGameOpt.get();
            if(mainGame.getType() != null || mainGame.getType().size() != 0) {
                List<Game> list = new ArrayList<>();
                for(String s:mainGame.getType()){
                    Page<Game> comp = searchByConditions(s,s,"","","",1,1);
                    list.addAll(comp.getContent());
                    break;
                }
                System.out.println("666");
                return list;
            }
            // TODO
        }catch (Exception e){
            System.out.println("666777");
            e.printStackTrace();
            return new ArrayList<>();
        }
        System.out.println("666555");
        return new ArrayList<>();
    }
    @Override
    public Game searchByName(String name) {
        return gameRepository.searchByName(name);
    }
}
