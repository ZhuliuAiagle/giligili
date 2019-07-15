package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.model.Game;
import zju.se.giligili.model.News;
import zju.se.giligili.model.Wordcloud;
import zju.se.giligili.service.BaiduIndexService;
import zju.se.giligili.service.GameService;
import zju.se.giligili.service.NewsService;
import zju.se.giligili.service.WordcloudService;

import java.util.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private GameService gameService;
    @Autowired
    private BaiduIndexService baiduIndexService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private WordcloudService wordcloudService;
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
                                  @RequestParam(name="isOrdered",required = false, defaultValue = "0") int isOrdered,
                                  @RequestParam(name="year",required = false, defaultValue = "") String year,
                                  @RequestParam(name="page",required=false,defaultValue="1") int page){
        Map ret = new HashMap();
        try {
            Page<Game> games = gameService.searchByConditions(key, type, theme, mode, year, page, isOrdered);
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
    @RequestMapping("/game")
    public Map game(@RequestParam(name="id") String id) throws Exception {
        Optional<Game> game = null;
        List<Game> competitors = null;
        String gameName = "";
        if(!id.equals("")) {
            game = gameService.findOneById(id);
            competitors = gameService.getCompetitors(id);
        }
        Map ret = new HashMap();
        if(game == null){
            ret.put("status",404);
            ret.put("errMsg","No such id or name");
            return ret;
        }
        ret.put("status",200);
        Game g;
        try {
            g = game.get();
            if((Double)g.getAvgScore() > 10) g.setAvgScore(10);
        }catch (NoSuchElementException e){
            ret.put("status",404);
            ret.put("errMsg","No such id or name");
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
        // 百度指数初始化
        BaiduIndex baiduIndex = null;
        // 新闻初始化
        List news = new ArrayList();
        // 词云链接初始化
        Wordcloud wc = new Wordcloud();
        if(g.getYouminData() != null && !g.getYouminData().isEmpty()){
            Map youminData = g.getYouminData();
            if(youminData.containsKey("competitorInfo")) youminData.remove("competitorInfo");
            String youminName = (String)youminData.get("name");
            // 获取youminname
            gameName = youminName;
            List<Map> youminScore = youminData.containsKey("score")?(List)youminData.get("score"):new ArrayList();
            if(youminScore == null) youminScore = new ArrayList();
            newYouminData.put("score",youminScore);
            // 根据youminName获取百度指数
            try {
                baiduIndex = baiduIndexService.getIndex(youminName);
            }catch(Exception e){
                e.printStackTrace();
                baiduIndex = null;
            }
            // 根据youminName获取新闻
            try{
                news = newsService.findAllByName(youminName);
                if(news == null || news.size() <= 0){
                    news = new ArrayList();
                }
                else {
                    News member = (News) news.get(0);
                    news = member.getNews();
                }
            }catch(Exception e){
                e.printStackTrace();
                news = null;
            }
            // 根据youminName获取词云
            try {
                wc = wordcloudService.getByName(youminName);
                if (wc == null) {
                    wc = new Wordcloud();
                }
            }catch (Exception e){
                e.printStackTrace();
                wc = null;
            }
        }
        if(news != null && news.size() > 0){
            if(((Map)(news.get(0))).isEmpty()){news = null;}
        }
        if(baiduIndex == null) ((Map)(ret.get("data"))).put("baiduData",new HashMap());
        else ((Map)(ret.get("data"))).put("baiduData",baiduIndex);
        if(news == null || news.size() <= 0) ((Map)(ret.get("data"))).put("news",new ArrayList());
        else ((Map)(ret.get("data"))).put("news",news);
        ((Map)(ret.get("data"))).put("score",(newYouminData.get("score") == null)?new ArrayList():newYouminData.get("score"));
        List compli = new ArrayList();
        for (Game gi : competitors){
            Map compMap = new HashMap();
            compMap.put("_id", gi.get_id());
            compMap.put("name", gi.getName());
            compli.add(compMap);
        }
        ((Map)(ret.get("data"))).put("competitors",compli);
        if(wc == null || wc.getPic_url() == null) ((Map)(ret.get("data"))).put("wordcloud","");
        else  ((Map)(ret.get("data"))).put("wordcloud",wc.getPic_url());
        ((Game)(((Map)(ret.get("data"))).get("tgbusData"))).setYouminData(null);
        return ret;
    }
}
