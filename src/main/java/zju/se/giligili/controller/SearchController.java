package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.model.Game;
import zju.se.giligili.service.BaiduIndexService;
import zju.se.giligili.service.GameService;

import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private GameService gameService;
    @Autowired
    private BaiduIndexService baiduIndexService;
    @RequestMapping("/games")
    public Map search(@RequestParam("key") String key){
        Map ret = new HashMap();
        try {
            List<Game> games = gameService.findAllByName(key);
            if(games == null){
                ret.put("status",200);
                ret.put("results_count", 0);
                ret.put("data", new ArrayList<>());
                return ret;
            }
            else{
                ret.put("status",200);
                ret.put("resultsCount", games.size());
                ret.put("data", games);
                return ret;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            ret.put("status",500);
            ret.put("errMsg",e.getMessage());
            return ret;
        }
    }
    @RequestMapping("/s")
    public Map searchByConditions(@RequestParam(name="key") String key,
                                  @RequestParam(name="type",required=false,defaultValue="") String type,
                                  @RequestParam(name="theme",required=false,defaultValue="") String theme,
                                  @RequestParam(name="mode",required=false,defaultValue="") String mode,
                                  @RequestParam(name="page",required=false,defaultValue="1") int page){
        Map ret = new HashMap();
        try {
            Page<Game> games = gameService.searchByConditions(key, type, theme, mode, page);
            if(games == null){
                ret.put("status",200);
                ret.put("results_count", 0);
                ret.put("data", new ArrayList());
                return ret;
            }
            else{
                ret.put("status",200);
                ret.put("resultsCount", games.getSize());
                ret.put("data", games);
                return ret;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            ret.put("status",500);
            ret.put("errMsg",e.getMessage());
            return ret;
        }
    }
    @RequestMapping("/game/{id}")
    public Map game(@PathVariable String id){
        Optional<Game> game = gameService.findOneById(id);
        Map ret = new HashMap();
        if(game == null){
            ret.put("status",404);
            ret.put("errMsg","No such id");
            return ret;
        }
        ret.put("status",200);
        Game g;
        try {
            g = game.get();
        }catch (NoSuchElementException e){
            ret.put("status",404);
            ret.put("errMsg","No such id");
            return ret;
        }catch(Exception e){
            e.printStackTrace();
            ret.put("status",500);
            ret.put("errMsg",e.getMessage());
            return ret;
        }
        ret.put("data",new HashMap());
        ((Map)(ret.get("data"))).put("tgbusData",g);
        Map newYouminData = new HashMap();
        BaiduIndex baiduIndex = null;
        if(g.getYouminData() != null && !g.getYouminData().isEmpty()){
            Map youminData = g.getYouminData();
            String youminName = (String)youminData.get("name");
            List<Map> youminScore = youminData.containsKey("score")?(List)youminData.get("score"):new ArrayList();
            List<Map> competitor = youminData.containsKey("competitorInfo")?(List)youminData.get("competitorInfo"):new ArrayList();
            newYouminData.put("score",youminScore);
            newYouminData.put("competitor", competitor);
            // 根据youminName获取百度指数
            try {
                baiduIndex = baiduIndexService.getIndex(youminName);
            }catch(Exception e){
                ret.put("status",500);
                ret.put("errMsg",e.getMessage());
                return ret;
            }
        }
        if(baiduIndex == null) ((Map)(ret.get("data"))).put("baiduData",new HashMap());
        else ((Map)(ret.get("data"))).put("baiduData",baiduIndex);
        ((Map)(ret.get("data"))).put("youminData",newYouminData);
        return ret;
    }
}
