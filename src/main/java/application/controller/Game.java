package application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.LinePuzzle;

@RestController
public class Game {
    @GetMapping("/game")
    public String hostGame(){
	int size = 7;
	String lp = new LinePuzzle(size).toString().replace("\n", "<br>").replace("\s", "&ensp;");
	System.out.println(lp);
        return "<p style=font-family:monospace;font-size:xx-large;text-align:center;display: flex;flex-direction:column;>" + lp + "</p>";
    }
}
