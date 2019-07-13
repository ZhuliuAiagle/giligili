package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.model.Game;
import zju.se.giligili.service.BaiduIndexService;
import zju.se.giligili.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private GameService gameService;
    private BaiduIndexService baiduIndexService;
    
    @RequestMapping("/game")
    public List<Game>  search(@RequestParam(value = "key") String key){
        System.out.println(key);
        List<Game> games = gameService.findAllByName(key);
        return games;

    }
    @RequestMapping("/index")
    public BaiduIndex index(@RequestParam(value = "key") String key){
        System.out.println(key);

        return baiduIndexService.getIndex(key);

    }
    @RequestMapping("/index1")
    public BaiduIndex index1(@RequestParam(value = "key") String key){
        System.out.println(key);

        return baiduIndexService.findIndexById(key);

    }
}

