package application.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import application.model.LinePuzzle;

@SpringBootApplication
public class Main {
    
    private static ConfigurableApplicationContext springContext;
    
    public static void main(String[] args) {
	//springContext = SpringApplication.run(Main.class, args); // Disabled For Debug
	
	// Debug Maze Creation
	int size = 5;
	System.out.println(new LinePuzzle(size));
    }
}
