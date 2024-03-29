package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.Game;
import zju.se.giligili.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @RequestMapping("/search")
    public String getUser(@RequestParam("name") String name){
        Game g = (Game) gameService.findAllByName(name);
        return g.getDescription();
    }
}
