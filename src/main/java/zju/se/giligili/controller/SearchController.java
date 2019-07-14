package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.service.GameService;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private GameService gameService;

    @RequestMapping("/games")
    public String search(@RequestParam("key") String key){
            return "no result";
    }

    @RequestMapping("/game/{id}")
    public String game(@PathVariable String id){
        return null;
    }
}
