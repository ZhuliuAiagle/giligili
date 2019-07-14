package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.Game;
import zju.se.giligili.service.GameService;

import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private GameService gameService;
    @RequestMapping("/games")
    public Map search(@RequestParam("key") String key){
        System.out.println(key);
        Map ret = new HashMap();
        try {
            List<Game> games = gameService.findAllByName(key);
            if(games == null){
                ret.put("status","SUCCESS");
                ret.put("results_count", 0);
                ret.put("data", new ArrayList<>());
                return ret;
            }
            else{
                ret.put("status","SUCCESS");
                ret.put("resultsCount", games.size());
                ret.put("data", games);
                return ret;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            ret.put("status","ERROR");
            ret.put("errMsg",e.getMessage());
            return ret;
        }
    }
    @RequestMapping("/game/{id}")
    public Optional<Game> game(@PathVariable String id){
        return gameService.findOneById(id);
    }
}
