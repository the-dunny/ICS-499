package tech.teamfour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import tech.teamfour.controllers.Game;
import tech.teamfour.model.LinePuzzle;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class GameApplication {
    public static void main(String... args) {
	SpringApplication.run(GameApplication.class, args);

	// Debug Maze Creation / Test

//	int size = 8;
//	LinePuzzle puzzle = new LinePuzzle(size);
//	Game game = new Game(puzzle);
//	try {
//	    game.RunGame();
//	} catch (InterruptedException e) {
//	    e.printStackTrace();
//	}
    }
}

/*@Component
class CapstoneCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PlayerServiceImpl impl;

    @Override
    public void run(String... args) throws Exception {
        impl.addPlayer(
                new Player(
                        0L, "Service2", "fromCMDRunner2", 12344
                )

        );
    }

 *//*
    @Override
    public void run(String... args) throws Exception {
        Player player = new Player();
        player.setPlayerID((playerRepository.count()+1));
        player.setUserName("cmdLineRunner3");
        player.setPassword("11_34_10_17");
        player.setBestScore(1234);

        playerRepository.save(player);
  *//*
}*/

