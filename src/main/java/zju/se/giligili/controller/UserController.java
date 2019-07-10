package zju.se.giligili.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zju.se.giligili.model.User;
import zju.se.giligili.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/add")
    public String insert(){
        User user = new User(16,""+16, 16);
        userService.insert(user);
        return "success";
    }
}
