package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.model.Game;
import zju.se.giligili.model.News;
import zju.se.giligili.service.BaiduIndexService;
import zju.se.giligili.service.GameService;
import zju.se.giligili.service.NewsService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private GameService gameService;
    @Autowired
    private BaiduIndexService baiduIndexService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("/game")
    public List<Game>  search(@RequestParam(value = "key") String key){
        System.out.println(key);
//        List<Game> games = gameService.findAllByName(key);
        List<Game> games = gameService.searchLazy(key);
        return games;



    }
    @RequestMapping("/index")
    public BaiduIndex index(@RequestParam(value = "key") String key){
        System.out.println(key);
        BaiduIndex baiduIndex = baiduIndexService.getIndex(key);
        return baiduIndex;

    }
    @RequestMapping("/index1")
    public BaiduIndex index1(@RequestParam(value = "key") String key){
        System.out.println(key);

        return baiduIndexService.findIndexById(key);

    }
    @RequestMapping("/news")
    public List<News> news(@RequestParam(value = "key") String key){
        System.out.println(key);

        return newsService.findAllByName(key);

    }
}

