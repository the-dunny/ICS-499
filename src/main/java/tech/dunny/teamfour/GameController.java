package tech.dunny.teamfour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/game")
    public String hostGame(){
        return "Under Construction";
    }
}
