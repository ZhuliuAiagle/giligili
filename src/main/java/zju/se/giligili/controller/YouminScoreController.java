package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.Game;
import zju.se.giligili.model.YouminScoreNew;
import zju.se.giligili.service.YouminScoreNewService;

@RestController
@RequestMapping("/score")
public class YouminScoreController {
    @Autowired
    YouminScoreNewService youminScoreNewService;
    @RequestMapping("/get")
    public String getScoreByName(@RequestParam("name") String name){
        YouminScoreNew g = youminScoreNewService.getScoreByName(name);
        return g.getUserScore().toString();
    }
}
