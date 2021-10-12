package application.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.LinePuzzle;
import application.model.Point;

@RestController
public class Game {
	LinePuzzle game;
	Point location;
	Point end;
	Scanner scanner = new Scanner(System.in);
	@GetMapping("/game")
	public String hostGame() {
		return "Under Construction";
	}
	public Game(LinePuzzle puzzle) {
		this.game = puzzle;
		this.location = game.getMainGrid().getStart();
		this.end = game.getMainGrid().getEnd();
	}
	public void RunGame() throws InterruptedException {
		
		while(!end.isTravel()){
			System.out.println("\r" + game.toString() + "\nEnter Up, Down, Left or Right to Move");
			System.out.println("S is the Start, E is the end, O are points you can travel on, ! are taveled points and X is unpassable");
			Move(scanner.nextLine());
			ChangeLocation();
		}
		scanner.close();
		System.out.println("Level Complete");
	}
	private void ChangeLocation() {
		game.getMainGrid().getVertexes().get(location.getX()).get(location.getY()).setVisited(true);
		
	}
	private void Move(String nextLine) throws InterruptedException {
		switch (nextLine.toUpperCase()){
		case "UP" : 
			if(checkUp())
				location.setY(location.getY() + 1);
			else {
				System.out.println("Illegal Move");
				Thread.sleep(10);
			}break;
		case "DOWN" : 
			if(checkDown())
				location.setY(location.getY() - 1);
			else {
				System.out.println("Illegal Move");
				Thread.sleep(10);
			}break;
			
		case "LEFT" : 
			if(checkLeft())
				location.setX(location.getX() - 1);
			else {
				System.out.println("Illegal Move");
				Thread.sleep(10);
			}break;
		case "RIGHT" :
			if(checkRight())
				location.setX(location.getX() + 1);
			else {
				System.out.println("Illegal Move");
				Thread.sleep(10);
			}break;
		}
		
	}
	
	private boolean checkUp() {
		boolean temp = false;
		if(location.getY() < game.getMainGrid().getVertexes().size() - 1) {
			temp = true;
		}else {
			return temp;
		}
		if(game.getMainGrid().getVertexes().get(location.getX()).get(location.getY() +1 ).isDead()) {
			temp = false;
		}
		return temp;
		
	}
	private boolean checkDown() {
		boolean temp = false;
		if(location.getY() > 0) {
			temp = true;
		}else {
			return temp;
		}
		if(game.getMainGrid().getVertexes().get(location.getX()).get(location.getY() - 1).isDead()) {
			temp = false;
		}
		return temp;
		
	}
	private boolean checkLeft() {
		boolean temp = false;
		if(location.getX() > 0) {
			temp = true;
		}else {
			return temp;
		}
		if(game.getMainGrid().getVertexes().get(location.getX() - 1).get(location.getY()).isDead()) {
			temp = false;
		}
		return temp;
		
	}
	private boolean checkRight() {
		boolean temp = false;
		if(location.getY() < game.getMainGrid().getVertexes().size() - 1) {
			temp = true;
		}else {
			return temp;
		}
		if(game.getMainGrid().getVertexes().get(location.getX() + 1).get(location.getY()).isDead()) {
			temp = false;
		}
		return temp;
		
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public LinePuzzle getGame() {
		return game;
	}
	public void setGame(LinePuzzle game) {
		this.game = game;
	}
}
