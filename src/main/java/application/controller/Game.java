package application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Game {
    @GetMapping("/game")
    public String hostGame(){
        return "Under Construction";
    }
}
