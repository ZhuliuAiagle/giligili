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
    public Page<Game> searchLazy(String key) {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "avgScore");
        return gameRepository.searchLazy(key, pageable);
    }

    @Override
    public Page<Game> searchByConditions(String key, String type, String theme, String mode, String year, int page, int isOrdered) {
        String conditions = "";
        String filter = "";
        Pageable descPageable = PageRequest.of((page - 1), 10, Sort.Direction.DESC, "avgScore");
        Pageable defaultPageble = PageRequest.of((page - 1), 10);
        Pageable ascPagebale = PageRequest.of((page - 1), 10, Sort.Direction.ASC, "avgScore");
        String format       = "{\"match\":{\"%s.keyword\":\"%s\"}}";
        String formatYear   = "\"gt\":\"%s\",\"lte\":\"%s\"";
        String formatPass   = "\"lte\":\"%s\"";
        if(type != null && !type.equals("")){
            conditions += ",";
            conditions += String.format(format,"type",type);

        }
        if(theme != null && !theme.equals("")){
            conditions += ",";
            conditions += String.format(format,"theme",theme);
        }
        if(mode != null && !mode.equals("")){
            conditions += ",";
            conditions += String.format(format,"mode",mode);

        }
//        if(!conditions.equals("")) {
//            StringBuilder b = new StringBuilder(conditions);
//            b.setLength(b.length() - 1);
//            conditions = b.toString();
//        }
        if(!year.equals("")) {
            String a = new Integer(Integer.parseInt(year) - 1).toString() + "-12-31";
            String b = year + "-12-31";
            filter = String.format(formatYear, a, b);
            System.out.println(filter);
        }
        if(year.equals("2009")){
            String a = year + "-12-31";
            filter = String.format(formatPass,a);
            System.out.println(filter);
        }
        System.out.println("Conditions: " + conditions);
        // 选择是默认搜索还是排序后搜索
        if(isOrdered == 1) {
            return gameRepository.searchByConditions(key, conditions, filter, descPageable);
        }
        else if(isOrdered == -1){
            return gameRepository.searchByConditions(key, conditions, filter, ascPagebale);
        }
        else {
            return gameRepository.searchByConditions(key, conditions, filter, defaultPageble);
        }
    }

    @Override
    public Page<Game> searchOnlyByConditions(String key, String type, String theme, String mode, String year, int page, int isOrdered) {
        String conditions = "";
        String filter = "";
        Pageable descPageable = PageRequest.of((page - 1), 10, Sort.Direction.DESC, "avgScore");
        Pageable defaultPageble = PageRequest.of((page - 1), 10);
        Pageable ascPagebale = PageRequest.of((page - 1), 10, Sort.Direction.ASC, "avgScore");
        String format       = "{\"match\":{\"%s.keyword\":\"%s\"}}";
        String formatYear   = "\"gt\":\"%s\",\"lte\":\"%s\"";
        String formatPass   = "\"lte\":\"%s\"";
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
            String a = new Integer(Integer.parseInt(year) - 1).toString() + "-12-31";
            String b = year + "-12-31";
            filter = String.format(formatYear, a, b);
            System.out.println(filter);
        }
        if(year.equals("2009")){
            String a = year + "-12-31";
            filter = String.format(formatPass,a);
            System.out.println(filter);
        }
        System.out.println("Conditions: " + conditions);
        // 选择是默认搜索还是排序后搜索
        if(isOrdered == 1) {
            return gameRepository.searchOnlyByConditions(key, conditions, filter, descPageable);
        }
        else if(isOrdered == -1){
            return gameRepository.searchOnlyByConditions(key, conditions, filter, ascPagebale);
        }
        else {
            return gameRepository.searchOnlyByConditions(key, conditions, filter, defaultPageble);
        }
    }

    @Override
    public List<Game> getCompetitors(String id) {
        Optional<Game> mainGameOpt = findOneById(id);
        try {
            Game mainGame = mainGameOpt.get();
            if(mainGame.getType() != null || mainGame.getType().size() != 0) {
                List<Game> list = new ArrayList<>();
                Set<Game> set = new HashSet<>();
                for(String s:mainGame.getType()){
                    Page<Game> comp = searchByConditions(s,s,"","","",1,1);
                    set.addAll(comp.getContent());
                }
                System.out.println("666");
                for(Game s: set){
                    if(s.get_id().equals(id)){
                        set.remove(s);
                        break;
                    }
                }
                return new ArrayList<>(set);
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
