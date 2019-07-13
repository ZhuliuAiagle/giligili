package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.service.GameService;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private GameService gameService;
    @RequestMapping("/s")
    public String search(String key){
            return "no result";
    }
}
